package ir.example.newstest.ui.home.xml

import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import ir.example.newstest.base.BaseViewModel
import ir.example.newstest.data.base.MutableResult
import ir.example.newstest.domain.pojo.Detail
import ir.example.newstest.domain.pojo.NewsFa
import ir.example.newstest.domain.usecase.favorite.AddFavorite
import ir.example.newstest.domain.usecase.favorite.RemoveFavorite
import ir.example.newstest.domain.usecase.news.XmlNewsUseCase
import javax.inject.Inject
import ir.example.newstest.domain.base.Result
import ir.example.newstest.ui.home.HomeFragmentDirections
import ir.example.newstest.util.ktx.collectOn
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class XmlFeedViewModel @Inject constructor(
    private val xmlNewsUseCase: XmlNewsUseCase,
    private val addFavorite: AddFavorite,
    private val removeFavorite: RemoveFavorite
) : BaseViewModel() {

    val newsResult = MutableResult<NewsFa>()
    val newsList = MutableResult<List<Detail>>()


    init {
        getNews()

        newsResult.observeForever {
            when (it) {
                is Result.Success -> {
                    newsList.postValue(
                        Result.Success(
                            it.data.channel?.details ?: return@observeForever
                        )
                    )
                }
            }
        }


        val newsList =newsResult.map {
            when(it){
                is Result.Success->{
                    return@map Result.Success(it.data.channel?.details)
                }
                else->{
                    null
                }
            }
        }
    }

    private fun getNews() = viewModelScope.launch {
        xmlNewsUseCase.invoke(Unit).collectOn(newsResult)
    }


    fun goToDetailNews(item: Detail) {
        item.link?.let {
            navigateTo(
                HomeFragmentDirections.actionHomeFragmentToDetailNavigation(it)
            )
        }
    }


    fun onFavoriteClicked(it: Detail) = viewModelScope.launch {
        if (it.isFavorite == true) {
            removeFavorite.invoke(it).collect()
        } else {
            addFavorite.invoke(it).collect()
        }
    }
}
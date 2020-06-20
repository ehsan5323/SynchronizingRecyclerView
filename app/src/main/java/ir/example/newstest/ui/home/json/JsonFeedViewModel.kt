package ir.example.newstest.ui.home.json

import androidx.lifecycle.viewModelScope
import ir.example.newstest.base.BaseViewModel
import ir.example.newstest.data.base.MutableResult
import ir.example.newstest.domain.pojo.Article
import ir.example.newstest.domain.pojo.NewsEn
import ir.example.newstest.domain.base.Result
import ir.example.newstest.domain.usecase.favorite.AddFavorite
import ir.example.newstest.domain.usecase.favorite.RemoveFavorite
import ir.example.newstest.domain.usecase.news.JsonNewsUseCase
import ir.example.newstest.ui.home.HomeFragmentDirections
import ir.example.newstest.util.ktx.collectOn
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val COUNTRY = "us"
private const val API_KEY = "2d248e49b4d44d3d8c93462557529cba"

class JsonFeedViewModel @Inject constructor(
    private val jsonNewsUseCase: JsonNewsUseCase,
    private val addFavorite: AddFavorite,
    private val removeFavorite: RemoveFavorite
) : BaseViewModel() {

    val newsResult = MutableResult<NewsEn>()
    val newsList = MutableResult<List<Article>>()


    init {
        getNews()

        newsResult.observeForever {
            when (it) {
                is Result.Success -> {
                    newsList.postValue(Result.Success(it.data.articles))
                }
            }
        }
    }

    private fun getNews() = viewModelScope.launch {
        jsonNewsUseCase.invoke(Pair(COUNTRY, API_KEY)).collectOn(newsResult)
    }


    fun goToDetailNews(item: Article) {
        navigateTo(
            HomeFragmentDirections.actionHomeFragmentToDetailNavigation(item.link)
        )
    }

    fun onFavoriteClicked(it: Article) = viewModelScope.launch {
        if (it.isFavorite) {
            removeFavorite.invoke(it).collect{
                when (it) {
                    is Result.Success -> {

                    }
                    is Result.Error -> {

                    }
                }
            }
        } else {
            addFavorite.invoke(it).collect()
        }
    }
}
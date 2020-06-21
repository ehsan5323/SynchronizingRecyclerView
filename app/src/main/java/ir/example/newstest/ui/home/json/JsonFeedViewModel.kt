package ir.example.newstest.ui.home.json

import androidx.lifecycle.viewModelScope
import ir.example.newstest.base.BaseViewModel
import ir.example.newstest.domain.pojo.Article
import ir.example.newstest.domain.pojo.NewsType
import ir.example.newstest.domain.pojo.XmlNewsReq
import ir.example.newstest.domain.usecase.favorite.AddArticleFavoriteUseCase
import ir.example.newstest.domain.usecase.favorite.DeleteArticleFavoriteUseCase
import ir.example.newstest.domain.usecase.news.JsonNewsUseCase
import ir.example.newstest.ui.home.HomeFragmentDirections
import ir.example.newstest.util.MutableLiveListResult
import ir.example.newstest.util.ktx.collectOn
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

const val COUNTRY = "us"
const val API_KEY = "2d248e49b4d44d3d8c93462557529cba"

class JsonFeedViewModel @Inject constructor(
    private val jsonNewsUseCase: JsonNewsUseCase,
    private val addArticleFavoriteUseCase: AddArticleFavoriteUseCase,
    private val deleteArticleFavoriteUseCase: DeleteArticleFavoriteUseCase
) : BaseViewModel() {

    val list = MutableLiveListResult<Article>()
//        .asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)

    init {
        getNews()
    }


    private fun getNews() = viewModelScope.launch {
        jsonNewsUseCase(XmlNewsReq(COUNTRY, API_KEY)).collectOn(list)
    }

    fun goToDetailNews(item: Article) {
        navigateTo(
            HomeFragmentDirections.actionHomeFragmentToDetailNavigation(
                item.link,
                item.link,
                NewsType.ARTICLE
            )
        )
    }


    fun onFavoriteClicked(article: Article) = viewModelScope.launch {
        /*deleteArticleFavoriteUseCase(article.link).collect {
            when (it) {
                is Result.Loading -> {
                    val newList = (list.value as? Result.Success)?.data?.toMutableList() ?: return@collect
                    val index = newList.indexOfFirst { it.link == article.link }
                    newList[index] = article.copy(isFavorite = false)
                    list.value = Result.Success(newList)
                }
                is Result.Error
            }
        }*/
        if (article.isFavorite == true)
            deleteArticleFavoriteUseCase(article.link).collect()
        else
            addArticleFavoriteUseCase(article.link).collect()
    }
}
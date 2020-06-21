package ir.example.newstest.ui.home.json

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import ir.example.newstest.base.BaseViewModel
import ir.example.newstest.domain.pojo.Article
import ir.example.newstest.domain.pojo.News
import ir.example.newstest.domain.pojo.NewsType
import ir.example.newstest.domain.pojo.XmlNewsReq
import ir.example.newstest.domain.usecase.favorite.AddArticleFavoriteUseCase
import ir.example.newstest.domain.usecase.favorite.DeleteArticleFavoriteUseCase
import ir.example.newstest.domain.usecase.news.GetJsonNewsUseCase
import ir.example.newstest.ui.home.HomeFragmentDirections
import ir.example.newstest.util.LiveListResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

const val COUNTRY = "us"
const val API_KEY = "2d248e49b4d44d3d8c93462557529cba"

class JsonFeedViewModel @Inject constructor(
    getJsonNewsUseCase: GetJsonNewsUseCase,
    private val addArticleFavoriteUseCase: AddArticleFavoriteUseCase,
    private val deleteArticleFavoriteUseCase: DeleteArticleFavoriteUseCase
) : BaseViewModel() {

    val list: LiveListResult<News> = getJsonNewsUseCase(XmlNewsReq(COUNTRY, API_KEY))
        .asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)

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
        if (article.isFavorite == true)
            deleteArticleFavoriteUseCase(article.link).collect()
        else
            addArticleFavoriteUseCase(article.link).collect()

        //TODO: Implementation isLoading
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
    }
}
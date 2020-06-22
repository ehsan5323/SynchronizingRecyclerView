package ir.example.newstest.ui.favorite

import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import ir.example.newstest.base.BaseViewModel
import ir.example.newstest.domain.base.Result
import ir.example.newstest.domain.pojo.Article
import ir.example.newstest.domain.pojo.Detail
import ir.example.newstest.domain.pojo.News
import ir.example.newstest.domain.pojo.NewsType
import ir.example.newstest.domain.pojo.req.XmlNewsReq
import ir.example.newstest.domain.usecase.favorite.DeleteArticleFavoriteUseCase
import ir.example.newstest.domain.usecase.favorite.DeleteDetailFavoriteUseCase
import ir.example.newstest.domain.usecase.favorite.GetAllFavoriteUseCase
import ir.example.newstest.ui.home.json.API_KEY
import ir.example.newstest.ui.home.json.COUNTRY
import ir.example.newstest.util.LiveListResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    getAllFavoriteUseCase: GetAllFavoriteUseCase,
    private val deleteArticleFavoriteUseCase: DeleteArticleFavoriteUseCase,
    private val deleteDetailFavoriteUseCase: DeleteDetailFavoriteUseCase
) : BaseViewModel() {

    val list: LiveListResult<News> = getAllFavoriteUseCase(XmlNewsReq(COUNTRY, API_KEY))
        .asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)

    val isEmpty = list.map {
        if (it is Result.Success) it.data.isNullOrEmpty() else true
    }

    fun goToDetailNews(item: News) {
        when (item) {
            is Article -> {
                navigateTo(
                    FavoriteFragmentDirections.actionFavoriteFragmentToDetailNavigation(
                        item.link, item.link, NewsType.ARTICLE
                    )
                )
            }
            is Detail -> {
                item.link?.let {
                    navigateTo(
                        FavoriteFragmentDirections.actionFavoriteFragmentToDetailNavigation(
                            it, item.guid, NewsType.DETAIL
                        )
                    )
                }
            }
        }
    }

    fun onFavoriteClicked(item: News) = viewModelScope.launch {
        when (item) {
            is Article -> {
                deleteArticleFavoriteUseCase(item.link).collect()
            }
            is Detail -> {
                deleteDetailFavoriteUseCase(item.guid).collect()
            }
        }
    }
}
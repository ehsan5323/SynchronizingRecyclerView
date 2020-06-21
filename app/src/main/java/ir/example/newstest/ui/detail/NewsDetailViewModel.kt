package ir.example.newstest.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.example.newstest.base.BaseViewModel
import ir.example.newstest.domain.base.Result
import ir.example.newstest.domain.pojo.NewsType
import ir.example.newstest.domain.usecase.favorite.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class NewsDetailViewModel @Inject constructor(
    private val isFavoriteDetailUseCase: IsFavoriteDetailUseCase,
    private val isFavoriteArticleUseCase: IsFavoriteArticleUseCase,
    private val addDetailFavoriteUseCase: AddDetailFavoriteUseCase,
    private val addArticleFavoriteUseCase: AddArticleFavoriteUseCase,
    private val deleteArticleFavoriteUseCase: DeleteArticleFavoriteUseCase,
    private val deleteDetailFavoriteUseCase: DeleteDetailFavoriteUseCase
) : BaseViewModel() {

    var newsType = NewsType.ARTICLE
    var dbKey: String? = null

    fun onFavoriteClicked() = viewModelScope.launch {
        if (isFavorite.value == true) {
            when (newsType) {
                NewsType.DETAIL -> {
                    deleteDetailFavoriteUseCase(dbKey ?: return@launch).collect()
                }
                NewsType.ARTICLE -> {
                    deleteArticleFavoriteUseCase(dbKey ?: return@launch).collect()
                }
            }
        } else {
            when (newsType) {
                NewsType.DETAIL -> {
                    addDetailFavoriteUseCase(dbKey ?: return@launch).collect()
                }
                NewsType.ARTICLE -> {
                    addArticleFavoriteUseCase(dbKey ?: return@launch).collect()
                }
            }
        }
        checkFavorite(newsType)
    }

    val isFavorite = MutableLiveData(false)

    private fun checkFavorite(newsType: NewsType) = viewModelScope.launch {
        when (newsType) {
            NewsType.DETAIL -> {
                isFavoriteDetail()
            }
            NewsType.ARTICLE -> {
                isFavoriteArticle()
            }
        }
    }

    private fun isFavoriteDetail() = viewModelScope.launch {
        isFavoriteDetailUseCase(dbKey ?: return@launch).collect {
            when (it) {
                is Result.Success -> {
                    isFavorite.value = it.data
                }
            }
        }
    }

    private fun isFavoriteArticle() = viewModelScope.launch {
        isFavoriteArticleUseCase(dbKey ?: return@launch).collect {
            when (it) {
                is Result.Success -> {
                    isFavorite.value = it.data
                }
            }
        }
    }

    fun setArgs(args: NewsDetailFragmentArgs) {
        newsType = args.newsType
        dbKey = args.dbKey
        checkFavorite(newsType)
    }
}
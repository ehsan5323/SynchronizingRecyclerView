package ir.example.newstest.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.example.newstest.base.BaseViewModel
import ir.example.newstest.data.base.MutableResult
import ir.example.newstest.domain.pojo.News
import ir.example.newstest.domain.usecase.favorite.AllNews
import ir.example.newstest.domain.usecase.favorite.RemoveFavorite
import ir.example.newstest.util.ktx.collectOn
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val allNews: AllNews,
    private val removeFavorite: RemoveFavorite
) : BaseViewModel() {

    val newsResult = MutableResult<List<News>>()

    init {
        getAllNews()
    }

    private fun getAllNews() = viewModelScope.launch {
        allNews.invoke(Unit).collectOn(newsResult)

    }

    fun goToDetailNews(item: News) {
        item.link?.let {
            navigateTo(
                FavoriteFragmentDirections.actionFavoriteFragmentToDetailNavigation(it)
            )
        }
    }

    fun onFavoriteClicked(it: News) = viewModelScope.launch {
        removeFavorite.invoke(it).collect()
        getAllNews()
    }
}
package ir.example.newstest.ui.home.xml

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import ir.example.newstest.base.BaseViewModel
import ir.example.newstest.domain.pojo.Detail
import ir.example.newstest.domain.pojo.NewsType
import ir.example.newstest.domain.usecase.favorite.AddDetailFavoriteUseCase
import ir.example.newstest.domain.usecase.favorite.DeleteDetailFavoriteUseCase
import ir.example.newstest.domain.usecase.news.GetXmlNewsUseCase
import ir.example.newstest.ui.home.HomeFragmentDirections
import ir.example.newstest.util.LiveListResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class XmlFeedViewModel @Inject constructor(
    getXmlNewsUseCase: GetXmlNewsUseCase,
    private val addDetailFavoriteUseCase: AddDetailFavoriteUseCase,
    private val deleteDetailFavoriteUseCase: DeleteDetailFavoriteUseCase
) : BaseViewModel() {

    val list: LiveListResult<Detail> = getXmlNewsUseCase(Unit)
        .asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)

    fun goToDetailNews(item: Detail) {
        item.link?.let {
            navigateTo(
                HomeFragmentDirections.actionHomeFragmentToDetailNavigation(
                    it,
                    item.guid,
                    NewsType.DETAIL
                )
            )
        }
    }

    fun onFavoriteClicked(detail: Detail) = viewModelScope.launch {
        if (detail.isFavorite == true)
            deleteDetailFavoriteUseCase(detail.guid).collect()
        else
            addDetailFavoriteUseCase(detail.guid).collect()
    }
}
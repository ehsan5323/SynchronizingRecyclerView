package ir.example.newstest.ui.home

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import ir.example.newstest.R
import ir.example.newstest.base.BaseViewModel
import ir.example.newstest.domain.pojo.News
import ir.example.newstest.domain.pojo.req.XmlNewsReq
import ir.example.newstest.domain.usecase.favorite.GetAllFavoriteUseCase
import ir.example.newstest.util.LiveListResult
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

const val COUNTRY = "us"
const val API_KEY = "2d248e49b4d44d3d8c93462557529cba"

class HomeViewModel  @Inject constructor(getJsonNewsUseCase: GetAllFavoriteUseCase) : BaseViewModel() {

    val list: LiveListResult<News> = getJsonNewsUseCase(XmlNewsReq(COUNTRY, API_KEY))
        .asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)

    val picList = mutableListOf<Int>()

    init {
        picList.add(R.drawable.season_spring_a)
        picList.add(R.drawable.season_summer_a)
        picList.add(R.drawable.season_fall_a)
        picList.add(R.drawable.season_winter_a)
    }

}
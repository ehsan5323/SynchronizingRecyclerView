package ir.example.newstest.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ir.example.newstest.R
import ir.example.newstest.base.BaseViewModel
import ir.example.newstest.domain.pojo.MetaData
import ir.example.newstest.domain.pojo.News
import ir.example.newstest.domain.usecase.GetMockMetaDataUseCase
import ir.example.newstest.domain.usecase.GetMockNewsUseCase
import javax.inject.Inject
import kotlin.math.roundToInt

class HomeViewModel @Inject constructor(
    mockNewsUseCase: GetMockNewsUseCase,
    mockMetaDataUseCase: GetMockMetaDataUseCase
) :
    BaseViewModel() {

    val scrollByValue: LiveData<Int>
        get() = _scrollByValue
    private val _scrollByValue = MutableLiveData<Int>()

    fun scrollCalculate(dy: Int, seasonWidthSize: Int, sumItemSize: Int) {
        val percentage = seasonWidthSize.toFloat() / sumItemSize.toFloat()
        val offset = (dy * percentage).roundToInt()
        _scrollByValue.value = offset
        Log.d("RecyclerViewScrollBy", "percentage: $percentage")
        Log.d("RecyclerViewScrollBy", "horizontalScrollOffset: $offset")
    }

    private var resultList = mutableListOf<News>()
    private var metaDataResultList = mutableListOf<MetaData>()

    val list = MutableLiveData<MutableList<News>>()
    val metaDataList = MutableLiveData<MutableList<MetaData>>()

    val seasonChangerList = MutableLiveData<MutableList<Int>>()

    private val picList = mutableListOf<Int>()

    init {
        picList.add(R.drawable.season_spring_a)
        picList.add(R.drawable.season_spring_b)
        picList.add(R.drawable.season_spring_c)
        picList.add(R.drawable.season_summer_a)
        picList.add(R.drawable.season_summer_b)
        picList.add(R.drawable.season_summer_c)
        picList.add(R.drawable.season_fall_a)
        picList.add(R.drawable.season_fall_b)
        picList.add(R.drawable.season_fall_c)
        picList.add(R.drawable.season_winter_a)
        picList.add(R.drawable.season_winter_b)
        picList.add(R.drawable.season_winter_c)
        picList.add(R.drawable.season_spring_a)
        picList.add(R.drawable.season_spring_b)
        picList.add(R.drawable.season_spring_c)

        seasonChangerList.value = picList
    }

    init {
        resultList = mockNewsUseCase(Unit)
        list.value = resultList
        metaDataResultList = mockMetaDataUseCase(Unit)
        metaDataList.value = metaDataResultList
    }


}
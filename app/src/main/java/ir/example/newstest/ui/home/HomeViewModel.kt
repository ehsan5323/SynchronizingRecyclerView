package ir.example.newstest.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.example.newstest.R
import ir.example.newstest.base.BaseViewModel
import ir.example.newstest.domain.pojo.*
import ir.example.newstest.domain.usecase.GetMockMetaDataUseCase
import ir.example.newstest.domain.usecase.GetMockNewsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

    val seasonSize: LiveData<SeasonSize>
        get() = _seasonSize
    private val _seasonSize = MutableLiveData<SeasonSize>()


    var seasonWidthSize = 0

    fun scrollCalculate(dy: Int, sumItemSize: Int) {
        val percentage = seasonWidthSize.toFloat() / sumItemSize.toFloat()
        val offset = (dy * percentage).roundToInt()
        _scrollByValue.value = offset
    }

    fun setSeasonWith(width: Int) {
        seasonWidthSize = width
        Log.d("RecyclerViewScrollBy", "list_news2 seasonWidthSize: $seasonWidthSize")

    }

    private var itemSizeTypeB = 0
    fun setItemSizeB(itemSizeTypeB: Int) {
        this.itemSizeTypeB = itemSizeTypeB
        Log.d("RecyclerViewScrollBy", "list_news itemSizeTypeB: $itemSizeTypeB")
    }

    private var itemSizeTypeA = 0
    fun setItemSizeA(itemSizeTypeA: Int) {
        this.itemSizeTypeA = itemSizeTypeA
        Log.d("RecyclerViewScrollBy", "list_news itemSizeTypeA: $itemSizeTypeA")
    }

    private var resultList = mutableListOf<News>()
    private var metaDataResultList = mutableListOf<MetaData>()

    val list = MutableLiveData<MutableList<News>>()

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
        setSize(metaDataResultList)
    }

    private fun setSize(metaDatas: MutableList<MetaData>) =
        viewModelScope.launch {
            delay(100)
            var sumSpringItemTypeA = 0
            var sumSpringNItemTypeA = 0
            var sumSummerItemTypeA = 0
            var sumFallItemTypeA = 0
            var sumWinterItemTypeA = 0

            var sumSpringItemTypeB = 0
            var sumSpringNItemTypeB = 0
            var sumSummerItemTypeB = 0
            var sumFallItemTypeB = 0
            var sumWinterItemTypeB = 0

            metaDatas.forEach { item ->
                when (item.season) {
                    Season.SPRING -> {
                        when (item.itemType) {
                            ItemType.MEDIUM -> {
                                sumSpringItemTypeA++
                            }
                            ItemType.LARGE -> {
                                sumSpringItemTypeB++
                            }
                        }
                    }
                    Season.SUMMER -> {
                        when (item.itemType) {
                            ItemType.MEDIUM -> {
                                sumSummerItemTypeA++
                            }
                            ItemType.LARGE -> {
                                sumSummerItemTypeB++
                            }
                        }
                    }
                    Season.FALL -> {
                        when (item.itemType) {
                            ItemType.MEDIUM -> {
                                sumFallItemTypeA++
                            }
                            ItemType.LARGE -> {
                                sumFallItemTypeB++
                            }
                        }
                    }
                    Season.WINTER -> {
                        when (item.itemType) {
                            ItemType.MEDIUM -> {
                                sumWinterItemTypeA++
                            }
                            ItemType.LARGE -> {
                                sumWinterItemTypeB++
                            }
                        }
                    }
                    Season.SPRING_N -> {
                        when (item.itemType) {
                            ItemType.MEDIUM -> {
                                sumSpringNItemTypeA++
                            }
                            ItemType.LARGE -> {
                                sumSpringNItemTypeB++
                            }
                        }
                    }
                }
            }
            val seasonSize = SeasonSize(
                sumSpringSize = (sumSpringItemTypeA * itemSizeTypeA) + (sumSpringItemTypeB * itemSizeTypeB),
                sumSummerSize = (sumSummerItemTypeA * itemSizeTypeA) + (sumSummerItemTypeB * itemSizeTypeB),
                sumFallSize = (sumFallItemTypeA * itemSizeTypeA) + (sumFallItemTypeB * itemSizeTypeB),
                sumWinterSize = (sumWinterItemTypeA * itemSizeTypeA) + (sumWinterItemTypeB * itemSizeTypeB),
                sumSpringNSize = (sumSpringNItemTypeA * itemSizeTypeA) + (sumSpringNItemTypeB * itemSizeTypeB)
            )
            _seasonSize.value = seasonSize

            Log.d(
                "RecyclerViewScrollBy",
                "list_news seasonSize.sumSpringSize: ${seasonSize.sumSpringSize}"
            )
            Log.d(
                "RecyclerViewScrollBy",
                "list_news seasonSize.sumSummerSize: ${seasonSize.sumSummerSize}"
            )
            Log.d(
                "RecyclerViewScrollBy",
                "list_news seasonSize.sumFallSize: ${seasonSize.sumFallSize}"
            )
            Log.d(
                "RecyclerViewScrollBy",
                "list_news seasonSize.sumWinterSize: ${seasonSize.sumWinterSize}"
            )
            Log.d(
                "RecyclerViewScrollBy",
                "list_news seasonSize.sumSpringNSize: ${seasonSize.sumSpringNSize}"
            )
        }
}

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

    val smoothScrollToPosition: LiveData<Int>
        get() = _smoothScrollToPosition
    private val _smoothScrollToPosition = MutableLiveData<Int>()

    val seasonSize: LiveData<SeasonSize>
        get() = _seasonSize
    private val _seasonSize = MutableLiveData<SeasonSize>()


    var seasonWidthSize = 0

    private fun scrollCalculate(dy: Int, sumItemSize: Int) {
        val percentage = seasonWidthSize.toFloat() / sumItemSize.toFloat()
        val offset = (dy * percentage).roundToInt()
        _scrollByValue.value = offset
    }

    fun setSeasonWidth(width: Int) {
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

    var scrollPosition = 0

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

                var springFlag = false
                var summerFlag = false
                var fallFlag = false
                var winterFlag = false
                var springNFlag = false

                metaDatas.forEach { item ->
                    when (item.season) {
                        Season.SPRING -> {
                            springFlag = true
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
                            summerFlag = true
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
                            fallFlag = true
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
                            winterFlag = true
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
                            springNFlag = true
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
                picList.clear()
                if (springFlag) {
                    picList.add(R.drawable.season_spring_a)
                    picList.add(R.drawable.season_spring_b)
                    picList.add(R.drawable.season_spring_c)
                }
                if (summerFlag) {
                    picList.add(R.drawable.season_summer_a)
                    picList.add(R.drawable.season_summer_b)
                    picList.add(R.drawable.season_summer_c)
                }
                if (fallFlag) {
                    picList.add(R.drawable.season_fall_a)
                    picList.add(R.drawable.season_fall_b)
                    picList.add(R.drawable.season_fall_c)
                }
                if (winterFlag) {
                    picList.add(R.drawable.season_winter_a)
                    picList.add(R.drawable.season_winter_b)
                    picList.add(R.drawable.season_winter_c)
                }
                if (springNFlag) {
                    picList.add(R.drawable.season_spring_a)
                    picList.add(R.drawable.season_spring_b)
                    picList.add(R.drawable.season_spring_c)
                }
                seasonChangerList.value = picList
            }

    fun scrollChanged(dy: Int) {
        scrollPosition += dy
        Log.d("RecyclerViewScrollBy", "scrollPosition: $scrollPosition")
        if (scrollPosition == 0) {
            _smoothScrollToPosition.value = 0
            Log.d("RecyclerViewScrollBy", "goto0000: $scrollPosition")
        }
        seasonSize.value?.apply {
            when (scrollPosition) {
                in 0 until sumSpringSize -> {
                    scrollCalculate(dy, sumSpringSize)
                }
                in sumSpringSize + 1 until sumSpringSize + sumSummerSize -> {
                    scrollCalculate(dy, sumSummerSize)
                }
                in sumSpringSize + sumSummerSize + 1 until sumSpringSize + sumSummerSize + sumFallSize -> {
                    scrollCalculate(dy, sumFallSize)
                }
                in sumSpringSize + sumSummerSize + sumFallSize + 1 until sumSpringSize + sumSummerSize + sumFallSize + sumWinterSize -> {
                    scrollCalculate(dy, sumWinterSize)
                }
                in sumSpringSize + sumSummerSize + sumFallSize + sumWinterSize + 1 until sumSpringSize + sumSummerSize + sumFallSize + sumWinterSize + sumSpringNSize -> {
                    scrollCalculate(dy, sumSpringNSize)
                }
            }
        }

    }
}

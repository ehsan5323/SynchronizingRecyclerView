package ir.example.newstest.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import ir.example.newstest.R
import ir.example.newstest.base.BaseFragment
import ir.example.newstest.base.ViewModelScope
import ir.example.newstest.databinding.FragmentHomeBinding
import ir.example.newstest.domain.pojo.ItemType
import ir.example.newstest.domain.pojo.MetaData
import ir.example.newstest.domain.pojo.News
import ir.example.newstest.domain.pojo.Season
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_news_en.*
import kotlinx.android.synthetic.main.item_news_fa.*
import kotlinx.android.synthetic.main.item_season_changer.*


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel: HomeViewModel by getLazyViewModel(ViewModelScope.FRAGMENT)

    override val layoutId: Int = R.layout.fragment_home

    private val adapter = NewsAdapter()
    private val adapter2 = SeasonChanger()


    private var newsList = mutableListOf<News>()

    private var metaDatas = mutableListOf<MetaData>()

    var seasonWidthSize = 0

    var sumSpringItemTypeA = 0
    var sumSummerItemTypeA = 0
    var sumFallItemTypeA = 0
    var sumWinterItemTypeA = 0

    var sumSpringItemTypeB = 0
    var sumSummerItemTypeB = 0
    var sumFallItemTypeB = 0
    var sumWinterItemTypeB = 0

    var springItemSizeTypeA = 0
    var summerItemSizeTypeA = 0
    var fallItemSizeTypeA = 0
    var winterItemSizeTypeA = 0

    var springItemSizeTypeB = 0
    var summerItemSizeTypeB = 0
    var fallItemSizeTypeB = 0
    var winterItemSizeTypeB = 0

    var sumSpringSize = 0
    var sumSummerSize = 0
    var sumFallSize = 0
    var sumWinterSize = 0


    override fun configEvents() {
        getSpringItemSizeTypeA(item_news_fa)
        getSpringItemSizeTypeB(item_news_en)
        getSeasonItemSize()

        list_news.adapter = adapter

        list_news2.adapter = adapter2

        list_news2.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                return true
            }
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })

        var scrollPosition = 0

        list_news.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                scrollPosition += dy

                val springPercentage: Float
                val summerPercentage: Float
                val fallPercentage: Float
                val winterPercentage: Float

                when (scrollPosition) {
                    in 0 until sumSpringSize -> {
                        springPercentage = seasonWidthSize.toFloat() / sumSpringSize.toFloat()
                        scrollBy(dy, springPercentage)
                    }
                    in sumSpringSize + 1 until sumSpringSize + sumSummerSize -> {
                        summerPercentage = seasonWidthSize.toFloat() / sumSummerSize.toFloat()
                        scrollBy(dy, summerPercentage)
                    }
                    in sumSpringSize + sumSummerSize + 1 until sumSpringSize + sumSummerSize + sumFallSize -> {
                        fallPercentage = seasonWidthSize.toFloat() / sumFallSize.toFloat()
                        scrollBy(dy, fallPercentage)
                    }
                    in sumSpringSize + sumSummerSize + sumFallSize + 1 until sumSpringSize + sumSummerSize + sumFallSize + sumWinterSize -> {
                        winterPercentage = seasonWidthSize.toFloat() / sumWinterSize.toFloat()
                        scrollBy(dy, winterPercentage)
                    }
                }

                Log.d("RecyclerViewScrollBy", "list_news scrollPosition: $scrollPosition")
            }
        })
        var sumOnScrolledList2 = 0
        list_news2.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                sumOnScrolledList2 += dx
                Log.d("RecyclerViewScrollBy", "list_news2 onScrolled sum: $sumOnScrolledList2")
            }
        })
    }

    private var sumHorizontalScroll = 0
    fun scrollBy(dy: Int, percentage: Float) {
        val decPart = percentage - percentage.toInt()
        var offset = (dy * percentage).toInt()
        if (decPart > 0.5f)
            offset++
        sumHorizontalScroll += offset
        Log.d("RecyclerViewScrollBy", "sumHorizontalScroll: $sumHorizontalScroll")
        list_news2.scrollBy(offset, 0)
    }

    private fun getViewSize(viewId: Int): Pair<Int, Int> {
        val inflater =
            context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val contentView: View = inflater.inflate(viewId, null, false)
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val width: Int = contentView.measuredWidth
        val height: Int = contentView.measuredHeight
        return Pair(width, height)
    }

    private fun getSpringItemSizeTypeA(constraintLayout: ConstraintLayout) {
        val vto: ViewTreeObserver = constraintLayout.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                constraintLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                springItemSizeTypeA = constraintLayout.measuredHeight
                Log.d("RecyclerViewScrollBy", "list_news ItemSizeTypeA: $springItemSizeTypeA")
                summerItemSizeTypeA = springItemSizeTypeA
                fallItemSizeTypeA = springItemSizeTypeA
                winterItemSizeTypeA = springItemSizeTypeA
                setItemSize()
            }
        })
    }

    private fun getSpringItemSizeTypeB(constraintLayout: ConstraintLayout) {
        val vto: ViewTreeObserver = constraintLayout.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                constraintLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                springItemSizeTypeB = constraintLayout.measuredHeight
                Log.d("RecyclerViewScrollBy", "list_news ItemSizeTypeB: $springItemSizeTypeB")
                summerItemSizeTypeB = springItemSizeTypeB
                fallItemSizeTypeB = springItemSizeTypeB
                winterItemSizeTypeB = springItemSizeTypeB
                setItemSize()
            }
        })
    }

    private fun getSeasonItemSize() {
        val vto: ViewTreeObserver = season_layout.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                season_layout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                seasonWidthSize = season_layout.measuredWidth * 3
                Log.d("RecyclerViewScrollBy", "list_news2 seasonWidthSize: $seasonWidthSize")
//                val viewItemSize = getViewSize(R.layout.item_season_changer)
//                seasonWidthSize = viewItemSize.first * 3
                setItemSize()
            }
        })
    }

    private fun setItemSize() {
        sumSpringItemTypeA = 0
        sumSummerItemTypeA = 0
        sumFallItemTypeA = 0
        sumWinterItemTypeA = 0

        sumSpringItemTypeB = 0
        sumSummerItemTypeB = 0
        sumFallItemTypeB = 0
        sumWinterItemTypeB = 0

        metaDatas.forEach {
            when (it.season) {
                Season.SPRING -> {
                    when (it.itemType) {
                        ItemType.MEDIUM -> {
                            sumSpringItemTypeA++
                        }
                        ItemType.LARGE -> {
                            sumSpringItemTypeB++
                        }
                    }
                }
                Season.SUMMER -> {
                    when (it.itemType) {
                        ItemType.MEDIUM -> {
                            sumSummerItemTypeA++
                        }
                        ItemType.LARGE -> {
                            sumSummerItemTypeB++
                        }
                    }
                }
                Season.FALL -> {
                    when (it.itemType) {
                        ItemType.MEDIUM -> {
                            sumFallItemTypeA++
                        }
                        ItemType.LARGE -> {
                            sumFallItemTypeB++
                        }
                    }
                }
                Season.WINTER -> {
                    when (it.itemType) {
                        ItemType.MEDIUM -> {
                            sumWinterItemTypeA++
                        }
                        ItemType.LARGE -> {
                            sumWinterItemTypeB++
                        }
                    }
                }
            }
        }
        sumSpringSize =
            (sumSpringItemTypeA * springItemSizeTypeA) + (sumSpringItemTypeB * springItemSizeTypeB)
        sumSummerSize =
            (sumSummerItemTypeA * summerItemSizeTypeA) + (sumSummerItemTypeB * summerItemSizeTypeB)
        sumFallSize =
            (sumFallItemTypeA * fallItemSizeTypeA) + (sumFallItemTypeB * fallItemSizeTypeB)
        sumWinterSize =
            (sumWinterItemTypeA * winterItemSizeTypeA) + (sumWinterItemTypeB * winterItemSizeTypeB)

        Log.d("RecyclerViewScrollBy", "list_news sumSpringSize: $sumSpringSize")
        Log.d("RecyclerViewScrollBy", "list_news sumSummerSize: $sumSummerSize")
        Log.d("RecyclerViewScrollBy", "list_news sumFallSize: $sumFallSize")
        Log.d("RecyclerViewScrollBy", "list_news sumWinterSize: $sumWinterSize")

    }

    override fun bindObservables() {
        viewModel.list.observe(this, Observer {
            newsList = it.toMutableList()
        })
        viewModel.metaDataList.observe(this, Observer {
            metaDatas = it.toMutableList()
            setItemSize()
        })

    }

    override fun initBinding() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            executePendingBindings()
        }
    }
}
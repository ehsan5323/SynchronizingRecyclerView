package ir.example.newstest.ui.home

import android.content.Context
import android.os.Build
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
import ir.example.newstest.domain.pojo.MetaData
import ir.example.newstest.domain.pojo.News
import ir.example.newstest.domain.pojo.Season
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_news_fa.*
import kotlinx.android.synthetic.main.item_season_changer.*


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel: HomeViewModel by getLazyViewModel(ViewModelScope.FRAGMENT)

    override val layoutId: Int = R.layout.fragment_home

    private val adapter = NewsAdapter()
    private val adapter2 = SeasonChanger()


    private val picList = mutableListOf<Int>()

    private var newsList = mutableListOf<News>()
    private var metaDatas = mutableListOf<MetaData>()

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
    }

    var sumSpringItem = 0
    var sumSummerItem = 0
    var sumFallItem = 0
    var sumWinterItem = 0

    var seasonWidthSize = 0
    var springItemSize = 0
    var summerItemSize = 0
    var fallItemSize = 0
    var winterItemSize = 0

    var sumSpringSize = 0
    var sumSummerSize = 0
    var sumFallSize = 0
    var sumWinterSize = 0


    override fun configEvents() {
        getSpringItemSize(item_news_fa)
        getSeasonItemSize()

        list_news.adapter = adapter

        list_news2.adapter = adapter2
        adapter2.submitList(picList)

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

                var springPercentage: Float = 0.0f
                var summerPercentage: Float = 0.0f
                var fallPercentage: Float = 0.0f
                var winterPercentage: Float = 0.0f

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
//                list_news2.scrollBy(dy, 0)

            }
        })
        var summH = 0
        list_news2.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                summH += dx
                Log.d("RecyclerViewScrollBy", "list_news2 onScrolled sum: $summH")
            }
        })
    }

    private var sumHorizontalScroll = 0
    fun scrollBy(dy: Int, percentage: Float) {
        val decPart = percentage - percentage.toInt()
        var offset = (dy * percentage).toInt()
        if (decPart > 0.5)
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

    private fun getSpringItemSize(constraintLayout: ConstraintLayout) {
        val vto: ViewTreeObserver = constraintLayout.viewTreeObserver
        var width = 0
        var height = 0
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    constraintLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this)
                } else {
                    constraintLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this)
                }
                width = constraintLayout.getMeasuredWidth()
                springItemSize = constraintLayout.getMeasuredHeight()
                Log.d("RecyclerViewScrollBy", "list_news ItemSize: $springItemSize")
                summerItemSize = springItemSize
                fallItemSize = springItemSize
                winterItemSize = springItemSize
                setItemSize()
            }
        })
    }

    private fun getSeasonItemSize() {
        val vto: ViewTreeObserver = season_layout.viewTreeObserver
        var width = 0
        var height = 0
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                season_layout.getViewTreeObserver().removeOnGlobalLayoutListener(this)
                seasonWidthSize = season_layout.getMeasuredWidth() * 3

                Log.d("RecyclerViewScrollBy", "list_news2 seasonWidthSize: $seasonWidthSize")

                val a = getViewSize(R.layout.item_season_changer)
//                seasonWidthSize = a.first * 3
                height = season_layout.getMeasuredHeight()
                setItemSize()
            }
        })
    }

    private fun setItemSize() {
        sumSpringItem = 0
        sumSummerItem = 0
        sumFallItem = 0
        sumWinterItem = 0
        metaDatas.forEach {
            when (it.season) {
                Season.SPRING -> {
                    sumSpringItem++
                }
                Season.SUMMER -> {
                    sumSummerItem++
                }
                Season.FALL -> {
                    sumFallItem++
                }
                Season.WINTER -> {
                    sumWinterItem++
                }
            }
        }
        sumSpringSize = sumSpringItem * springItemSize
        sumSummerSize = sumSummerItem * summerItemSize
        sumFallSize = sumFallItem * fallItemSize
        sumWinterSize = sumWinterItem * winterItemSize

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
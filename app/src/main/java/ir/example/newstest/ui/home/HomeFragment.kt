package ir.example.newstest.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
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
import ir.example.newstest.domain.util.Result
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel: HomeViewModel by getLazyViewModel(ViewModelScope.FRAGMENT)

    override val layoutId: Int = R.layout.fragment_home

    private val adapter = ArticleAdapter()
    private val adapter2 = SeasonChanger()


    val picList = mutableListOf<Int>()

    var newsList = mutableListOf<News>()

    init {
        picList.add(R.drawable.season_spring_a)
        picList.add(R.drawable.season_summer_a)
        picList.add(R.drawable.season_fall_a)
        picList.add(R.drawable.season_winter_a)


    }

    private val metaDatas = mutableListOf<MetaData>()

    override fun configEvents() {
        list_news.adapter = adapter

        list_news2.adapter = adapter2
        adapter2.submitList(picList)

        var sumSpringItem = 0
        var sumSummerItem = 0
        var sumFallItem = 0
        var sumWinterItem = 0
        metaDatas.forEach {
            when (it.season) {
                Season.SPRING -> {
                    sumSpringItem++
                }
                Season.SUMMER -> {
                    sumSpringItem++
                }
                Season.FALL -> {
                    sumFallItem++
                }
                Season.WINTER -> {
                    sumWinterItem++
                }
            }
        }
        var sumSpringSize = sumSpringItem * getViewSize(R.layout.item_news_fa).second
        var sumSummerSize = sumSummerItem * getViewSize(R.layout.item_news_fa).second
        var sumFallSize = sumFallItem * getViewSize(R.layout.item_news_fa).second
        var sumWinterSize = sumWinterItem * getViewSize(R.layout.item_news_fa).second

        val horizontalWith = getViewSize(R.layout.item_season_changer).first


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

//                when(scrollPosition){
//                    is 1 range->{
//
//                }
//                }

                Log.d("scrollPosition", "scrollPosition: $scrollPosition")

                list_news2.scrollBy(dy, 0)

            }
        })

        val newsFaSize = getViewSize(R.layout.item_news_fa)
        val newsEnSize = getViewSize(R.layout.item_news_en)
        val seasonSize = getViewSize(R.layout.item_season_changer)
        Log.d("getMeasuredWidth", "getMeasuredWidth: ${seasonSize.first}")
        Log.d("getMeasuredWidth", "getMeasuredheight: ${seasonSize.second}")

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


    override fun bindObservables() {
        viewModel.list.observe(this, Observer {
            newsList = if (it is Result.Success) it.data.toMutableList() else mutableListOf()
            repeat(newsList.size) { item ->
                if (item % 2 == 0)
                    metaDatas.add(MetaData((Season.SPRING), ItemType.MEDIUM))
                else metaDatas.add(MetaData((Season.SUMMER), ItemType.MEDIUM))

            }
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
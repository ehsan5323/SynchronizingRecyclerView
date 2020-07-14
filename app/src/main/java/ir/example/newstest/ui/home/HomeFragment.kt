package ir.example.newstest.ui.home

import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.example.newstest.base.BaseFragment
import ir.example.newstest.base.ViewModelScope
import ir.example.newstest.R
import ir.example.newstest.databinding.FragmentHomeBinding
import ir.example.newstest.domain.pojo.News
import ir.example.newstest.domain.util.Result
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.list_news

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

    var totalRecord = 0


    override fun configEvents() {
        list_news.adapter = adapter


        list_news2.adapter = adapter2

        list_news2.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener{
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
            }

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                return true
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }

        })

        val layoutManagerSlider = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        list_news2.layoutManager = layoutManagerSlider

        adapter2.submitList(picList)


        var scrollVal = 10

        var scrollPosition = 0

        scrollView.height

        list_news.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                scrollPosition += dy
                val withItem = SeasonChanger.widthItem

                val lm = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItem = lm.findFirstVisibleItemPosition()

                val percentage = (firstVisibleItem.toFloat() / totalRecord.toFloat())

                Log.d("percentage", "percentage: $percentage")
                Log.d("percentage", "firstVisibleItem: $firstVisibleItem")
                Log.d("percentage", "totalRecord: $totalRecord")
                Log.d(
                    "percentage",
                    "scrollTo scrollTo: ${percentage * scrollView.height.toFloat().toInt()}"
                )




                scrollVal = firstVisibleItem * withItem
//                scrollView.scrollTo(scrollVal, 0)

                scrollView.scrollTo((percentage * scrollView.height.toFloat()).toInt(), 0)

//                layoutManagerSlider.scrollToPositionWithOffset(dy,0)


                val aa = newsList[firstVisibleItem]

                list_news2.scrollBy(dy,0)


//                recyclerView.scrollTo()

                val handler = Handler()
                handler.postDelayed({ // Add the new influencers to the database
                    Log.d("qqqqqqqqqqqqqq", "scrollSeasonHeader: $dy")
                    Log.d("qqqqqqqqqqqqqq", "scrollSeasonHeader: $dy")
                    Log.d("qqqqqqqqqqqqqq", "withAll: $withItem")
                    Log.d("scrollView.height", "scrollView.height: ${scrollView.height}")
                    Log.d("scrollView.height", "scrollPosition: ${scrollPosition}")
                    if (withItem != 0) {
                        Log.d("qqqqqqqqqqqqqq", "dy/withAll: ${dy / withItem}")
//                        list_news2.scrollBy(dy / withAll, 0)
                    }

//                    scrollView.scrollTo(scrollVal, 0)

//                    list_news2.scrollBy(dx, 0)
//                    horizontalScroll(dy)

                }, 2)


            }
        })

        list_news2.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val handler = Handler()
                handler.postDelayed({ // Add the new influencers to the database

//                    list_news.scrollBy(0,dx)

//                    list_news.scrollBy(0, dx)
//                    horizontalScroll(dy)

                }, 200)

            }
        })
    }

    fun horizontalScroll(dy: Int) {
        list_news2.scrollBy(dy, 0)

//        var i = 0
//        val n: Int = list_news.childCount
//        while (i < n) {
//            list_news2.scrollBy(dx, 0)
//            ++i
//        }
    }

    fun verticalCScroll(dx: Int) {
        list_news.scrollBy(dx, 0)
    }

    override fun bindObservables() {
        viewModel.list.observeForever {
            newsList = if (it is Result.Success) it.data.toMutableList() else mutableListOf()
            totalRecord = newsList.size

        }


    }

    override fun initBinding() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            executePendingBindings()
        }
    }
}
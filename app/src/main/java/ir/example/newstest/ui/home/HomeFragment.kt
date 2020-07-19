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
import ir.example.newstest.domain.pojo.SeasonSize
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_news_en.*
import kotlinx.android.synthetic.main.item_news_fa.*
import kotlinx.android.synthetic.main.item_season_changer.*


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel: HomeViewModel by getLazyViewModel(ViewModelScope.FRAGMENT)

    override val layoutId: Int = R.layout.fragment_home

    private val adapter = NewsAdapter()
    private val adapter2 = SeasonChanger()

    var seasonSize: SeasonSize? = null

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

        list_news.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                viewModel.scrollChanged(dy)
            }
        })
        var sumOnScrolledList2 = 0
        list_news2.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                sumOnScrolledList2 += dx
                Log.d("RecyclerViewScrollBy", "sumOnScrolledList2: $sumOnScrolledList2")
            }
        })
    }

    private fun scrollBy(scrollOffset: Int) {
        list_news2.scrollBy(scrollOffset, 0)
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
                viewModel.setItemSizeA(constraintLayout.measuredHeight)
            }
        })
    }

    private fun getSpringItemSizeTypeB(constraintLayout: ConstraintLayout) {
        val vto: ViewTreeObserver = constraintLayout.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                constraintLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                viewModel.setItemSizeB(constraintLayout.measuredHeight)
            }
        })
    }

    private fun getSeasonItemSize() {
        val vto: ViewTreeObserver = season_layout.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                season_layout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                viewModel.setSeasonWidth(season_layout.measuredWidth * 3)
            }
        })
    }

    override fun bindObservables() {
        viewModel.seasonSize.observe(this, Observer {
            seasonSize = it
        })
        viewModel.seasonChangerList.observe(this, Observer {
            adapter2.submitList(it)
        })
        viewModel.scrollByValue.observe(this, Observer {
            scrollBy(it)
        })
        viewModel.smoothScrollToPosition.observe(this, Observer {
            list_news2.smoothScrollToPosition(it)
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
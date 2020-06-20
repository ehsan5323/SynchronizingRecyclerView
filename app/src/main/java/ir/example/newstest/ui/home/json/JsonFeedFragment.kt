package ir.example.newstest.ui.home.json

import ir.example.newstest.base.BaseFragment
import ir.example.newstest.base.ViewModelScope
import ir.example.newstest.R
import ir.example.newstest.databinding.FragmentJsonFeedBinding
import kotlinx.android.synthetic.main.fragment_xml_feed.*

class JsonFeedFragment : BaseFragment<JsonFeedViewModel, FragmentJsonFeedBinding>() {

    override val viewModel: JsonFeedViewModel by getLazyViewModel(ViewModelScope.FRAGMENT)

    override val layoutId: Int = R.layout.fragment_json_feed

    private val adapter = NewsEnAdapter()

    override fun configEvents() {
        list_news.adapter = adapter
        adapter.onItemClicked = { item, _ ->
//            viewModel.goToDetailNews(item)
        }
        adapter.onFavoriteClicked = {
//            viewModel.onFavoriteClicked(it)
        }
    }

    override fun bindObservables() {

    }

    override fun initBinding() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            executePendingBindings()
        }
    }
}
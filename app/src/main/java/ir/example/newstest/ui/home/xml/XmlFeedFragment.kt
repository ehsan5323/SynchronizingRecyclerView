package ir.example.newstest.ui.home.xml

import ir.example.newstest.R
import ir.example.newstest.base.BaseFragment
import ir.example.newstest.base.ViewModelScope
import ir.example.newstest.databinding.FragmentXmlFeedBinding
import kotlinx.android.synthetic.main.fragment_xml_feed.*

class XmlFeedFragment : BaseFragment<XmlFeedViewModel, FragmentXmlFeedBinding>() {

    override val viewModel: XmlFeedViewModel by getLazyViewModel(ViewModelScope.FRAGMENT)

    override val layoutId: Int = R.layout.fragment_xml_feed

    private val adapter = NewsFaAdapter()

    override fun configEvents() {
        list_news.adapter = adapter
        adapter.apply {
            onItemClicked = { item, _ ->
                viewModel.goToDetailNews(item)
            }
            onFavoriteClicked = {
                viewModel.onFavoriteClicked(it)
            }
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
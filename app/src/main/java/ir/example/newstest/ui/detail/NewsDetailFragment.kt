package ir.example.newstest.ui.detail

import androidx.navigation.fragment.navArgs
import ir.example.newstest.R
import ir.example.newstest.base.BaseFragment
import ir.example.newstest.base.ViewModelScope
import ir.example.newstest.databinding.FragmentDetailNewsBinding
import kotlinx.android.synthetic.main.fragment_detail_news.*

class NewsDetailFragment : BaseFragment<NewsDetailViewModel, FragmentDetailNewsBinding>() {

    override val viewModel: NewsDetailViewModel by getLazyViewModel(ViewModelScope.FRAGMENT)

    override val layoutId: Int = R.layout.fragment_detail_news

    private val args: NewsDetailFragmentArgs by navArgs()

    override fun configEvents() {
        my_web_view.apply {
            webViewClient =
                MyWebViewClient()
            loadUrl(args.newsLink)
        }
        viewModel.apply {
            newsType = args.newsType
            setArgs(args)
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
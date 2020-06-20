package ir.example.newstest.ui.home

import ir.example.newstest.base.BaseFragment
import ir.example.newstest.base.ViewModelScope
import ir.example.newstest.R
import ir.example.newstest.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel: HomeViewModel by getLazyViewModel(ViewModelScope.FRAGMENT)

    override val layoutId: Int = R.layout.fragment_home

    override fun configEvents() {
        val fragmentAdapter = HomePagerAdapter(childFragmentManager)
        view_pager.adapter = fragmentAdapter
        tabs_feed.setupWithViewPager(view_pager)
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
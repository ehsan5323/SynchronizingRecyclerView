package ir.example.newstest.ui.favorite

import ir.example.newstest.R
import ir.example.newstest.base.BaseFragment
import ir.example.newstest.base.ViewModelScope
import ir.example.newstest.databinding.FragmentFavoriteBinding
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : BaseFragment<FavoriteViewModel, FragmentFavoriteBinding>() {

    override val viewModel: FavoriteViewModel by getLazyViewModel(ViewModelScope.FRAGMENT)

    override val layoutId: Int = R.layout.fragment_favorite

    private val adapter = FavoriteAdapter()

    override fun configEvents() {
        list_favorite.adapter = adapter
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
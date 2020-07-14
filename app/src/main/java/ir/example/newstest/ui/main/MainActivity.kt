package ir.example.newstest.ui.main

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import ir.example.newstest.R
import ir.example.newstest.base.BaseActivity
import ir.example.newstest.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val layoutId: Int = R.layout.activity_main

    override val viewModel: MainViewModel by getLazyViewModel()

    override val navigationId = R.id.navigation_fragment

    override fun configEvents() {
    }

    override fun bindObservables() {
    }

    override fun initBinding() {
        binding.apply {
            lifecycleOwner = this@MainActivity
            vm = viewModel
            executePendingBindings()
        }
    }

}
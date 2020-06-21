package ir.example.newstest.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import dagger.android.support.DaggerAppCompatActivity
import ir.example.newstest.util.ConnectionLiveData
import ir.example.newstest.util.addNavigatorOn
import ir.example.newstest.util.observeActions
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : DaggerAppCompatActivity(),
    BaseView<VM, DB> {

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    inline fun <reified T : BaseViewModel> getLazyViewModel(): Lazy<T> =
        lazy { ViewModelProvider(this, viewModelFactory)[T::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        bindObservables()
        configEvents()
        initActions()
        initNavigator()
    }

    /**
     * Initialising binding lazily means it will only be initialized on access, not before
     */
    val binding by lazy {
        DataBindingUtil.setContentView(this, layoutId) as DB
    }

    /**
     * Events like adapter lambdas should be inside this overridden method
     */
    abstract fun configEvents()

    /**
     * observing on viewModel's live data should be inside this overridden method
     */
    abstract fun bindObservables()

    /**
     * This is the id of androidx.navigation.fragment.NavHostFragment that has an app:navGraph
     * This is needed in @BaseViewModel to be able to perform navigation inside viewModels
     */
    abstract val navigationId: Int

    private fun initActions() = observeActions(viewModel)

    private fun initNavigator() =
        addNavigatorOn(viewModel, Navigation.findNavController(this, navigationId))

    private fun checkInternetConnection() {
        ConnectionLiveData.observe(this) { isConnected ->
            onNetworkStateChanged(isConnected)
        }
    }

    open fun onNetworkStateChanged(isConnected: Boolean) {}
}

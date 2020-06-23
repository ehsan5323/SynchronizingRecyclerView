package ir.example.newstest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import ir.example.newstest.base.ViewModelScope.ACTIVITY
import ir.example.newstest.base.ViewModelScope.FRAGMENT
import ir.example.newstest.util.ConnectionLiveData
import ir.example.newstest.util.addNavigatorOn
import ir.example.newstest.util.observeActions
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> :
    DaggerFragment(), BaseView<VM, DB> {

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    inline fun <reified T : BaseViewModel> getLazyViewModel(scope: ViewModelScope): Lazy<T> =
        lazy {
            when (scope) {
                ACTIVITY -> ViewModelProvider(
                    requireActivity(),
                    viewModelFactory
                )[T::class.java]
                FRAGMENT -> ViewModelProvider(this, viewModelFactory)[T::class.java]
            }
        }

    open var binding: DB? = null

    private fun init(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        init(inflater, container)
        initBinding()
        bindObservables()
        initActions()
        initNavigator()
        checkInternetConnection()
        return binding?.root ?: View(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configEvents()
    }

    abstract fun configEvents()

    abstract fun bindObservables()

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initActions() = observeActions(viewModel)

    private fun initNavigator() {
        addNavigatorOn(viewModel, findNavController())
    }

    private fun checkInternetConnection() {
        ConnectionLiveData.observe(this) { isConnected ->
            onNetworkStateChanged(isConnected)
        }
    }
    open fun onNetworkStateChanged(isConnected: Boolean) {}
}


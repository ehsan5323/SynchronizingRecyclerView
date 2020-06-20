package ir.example.newstest.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

interface BaseView<VM : BaseViewModel, DB : ViewDataBinding> {

    /**
     * will be used for getting instance of ViewModels
     */
    val viewModelFactory: ViewModelProvider.Factory

    /**
     * default ViewModel of view, will be initialized by [viewModelFactory]
     */
    val viewModel: VM

    /**
     * Resource Id of main layout for view
     */
    val layoutId: Int

    /**
     * will be called after intialization of view
     *
     */
    fun initBinding()
}
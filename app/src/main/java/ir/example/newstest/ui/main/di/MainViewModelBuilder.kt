package ir.example.newstest.ui.main.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ir.example.newstest.di.viewmodel.ViewModelKey
import ir.example.newstest.ui.main.MainViewModel

@Module
abstract class MainViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindLoginSignUpViewModel(mainActivityViewModel: MainViewModel): ViewModel

}
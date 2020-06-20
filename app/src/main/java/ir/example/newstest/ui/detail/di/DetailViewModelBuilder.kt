package ir.example.newstest.ui.detail.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ir.example.newstest.di.viewmodel.ViewModelKey
import ir.example.newstest.ui.detail.NewsDetailViewModel

@Module
abstract class DetailViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(NewsDetailViewModel::class)
    abstract fun bindNewsDetailViewModel(homeViewModel: NewsDetailViewModel): ViewModel

}
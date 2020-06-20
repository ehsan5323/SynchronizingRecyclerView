package ir.example.newstest.ui.favorite.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ir.example.newstest.di.viewmodel.ViewModelKey
import ir.example.newstest.ui.favorite.FavoriteViewModel

@Module
abstract class FavoriteViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: FavoriteViewModel): ViewModel

}
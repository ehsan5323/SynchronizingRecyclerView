package ir.example.newstest.di.builder

import androidx.lifecycle.ViewModelProvider
import com.example.ehsantestproject01.di.viewmodel.EhsanTestViewModelFactory
import com.example.ehsantestproject01.ui.detail.di.DetailViewModelBuilder
import com.example.ehsantestproject01.ui.favorite.di.FavoriteViewModelBuilder
import com.example.ehsantestproject01.ui.home.di.HomeViewModelBuilder
import com.example.ehsantestproject01.ui.main.di.MainViewModelBuilder
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        MainViewModelBuilder::class,
        FavoriteViewModelBuilder::class,
        HomeViewModelBuilder::class,
        DetailViewModelBuilder::class
    ]
)
abstract class ViewModelBuilder {
    @Binds
    abstract fun bindViewModelFactory(ehsanTestViewModelFactory: EhsanTestViewModelFactory): ViewModelProvider.Factory
}
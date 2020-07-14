package ir.example.newstest.di.builder

import androidx.lifecycle.ViewModelProvider
import ir.example.newstest.di.viewmodel.NewsTestViewModelFactory
import ir.example.newstest.ui.home.di.HomeViewModelBuilder
import ir.example.newstest.ui.main.di.MainViewModelBuilder
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        MainViewModelBuilder::class,
        HomeViewModelBuilder::class
    ]
)
abstract class ViewModelBuilder {
    @Binds
    abstract fun bindViewModelFactory(newsTestViewModelFactory: NewsTestViewModelFactory): ViewModelProvider.Factory
}
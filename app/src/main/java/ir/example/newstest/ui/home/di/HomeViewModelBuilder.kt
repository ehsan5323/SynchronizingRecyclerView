package ir.example.newstest.ui.home.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ir.example.newstest.di.viewmodel.ViewModelKey
import ir.example.newstest.ui.home.HomeViewModel
import ir.example.newstest.ui.home.json.JsonFeedViewModel
import ir.example.newstest.ui.home.xml.XmlFeedViewModel

@Module
abstract class HomeViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(JsonFeedViewModel::class)
    abstract fun bindJsonFeedViewModel(jsonFeedViewModel: JsonFeedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(XmlFeedViewModel::class)
    abstract fun bindXmlFeedViewModel(xmlFeedViewModel: XmlFeedViewModel): ViewModel


}
package ir.example.newstest.ui.main.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ir.example.newstest.ui.detail.NewsDetailFragment
import ir.example.newstest.ui.favorite.FavoriteFragment
import ir.example.newstest.ui.home.HomeFragment
import ir.example.newstest.ui.home.json.JsonFeedFragment
import ir.example.newstest.ui.home.xml.XmlFeedFragment

@Module
abstract class MainFragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun provideFavoriteFragment(): FavoriteFragment

    @ContributesAndroidInjector
    abstract fun provideNewsDetailFragment(): NewsDetailFragment

    @ContributesAndroidInjector
    abstract fun provideJsonFeedFragmentBinding(): JsonFeedFragment

    @ContributesAndroidInjector
    abstract fun provideXmlFeedFragment(): XmlFeedFragment

}
package ir.example.newstest.ui.main.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ir.example.newstest.ui.home.HomeFragment

@Module
abstract class MainFragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideHomeFragment(): HomeFragment
}
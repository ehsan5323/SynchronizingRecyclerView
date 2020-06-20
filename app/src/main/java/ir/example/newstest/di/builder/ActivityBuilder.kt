package ir.example.newstest.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ir.example.newstest.ui.main.MainActivity
import ir.example.newstest.ui.main.di.MainFragmentProvider

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainFragmentProvider::class])
    internal abstract fun bindMainActivity(): MainActivity

}

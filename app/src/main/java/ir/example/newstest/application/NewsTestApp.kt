package ir.example.newstest.application

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import ir.example.newstest.di.component.DaggerAppComponent

class NewsTestApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.factory().create(this)

}
package ir.example.newstest.application

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class NewsTestApp : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        // Init Connection Detector
//        ConnectionLiveData.init(this)

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.factory().create(this)


}
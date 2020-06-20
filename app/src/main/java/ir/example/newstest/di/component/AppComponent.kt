package ir.example.newstest.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ir.example.newstest.application.NewsTestApp
import ir.example.newstest.di.builder.ActivityBuilder
import ir.example.newstest.di.module.AppModule
import ir.example.newstest.di.module.DatabaseModule
import ir.example.newstest.di.module.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (AndroidSupportInjectionModule::class),
        (AppModule::class),
        (NetworkModule::class),
        (DatabaseModule::class),
        (ActivityBuilder::class)]
)
interface AppComponent : AndroidInjector<NewsTestApp> {

    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<NewsTestApp> {
        interface Factory {
            fun create(@BindsInstance application: Context): AppComponent
        }
    }

}
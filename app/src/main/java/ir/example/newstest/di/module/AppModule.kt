package ir.example.newstest.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ir.example.newstest.application.NewsTestApp
import ir.example.newstest.di.builder.RepositoryBuilder
import ir.example.newstest.di.builder.ViewModelBuilder
import javax.inject.Singleton

@Module(includes = [ViewModelBuilder::class, RepositoryBuilder::class])
object AppModule {

    @Provides
    @Singleton
    fun provideContext(application: NewsTestApp): Context {
        return application
    }
}

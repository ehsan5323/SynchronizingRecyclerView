package ir.example.newstest.di.builder

import dagger.Binds
import dagger.Module
import ir.example.newstest.data.repository.NewsRepositoryImpl
import ir.example.newstest.domain.repository.NewsRepository

@Module
abstract class RepositoryBuilder {

    @Binds
    abstract fun bindsNewsRepository(homeRepositoryImpl: NewsRepositoryImpl): NewsRepository

}
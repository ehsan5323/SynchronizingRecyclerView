package ir.example.newstest.di.builder

import dagger.Binds
import dagger.Module
import ir.example.newstest.data.repository.FavoriteRepositoryImpl
import ir.example.newstest.data.repository.NewsRepositoryImpl
import ir.example.newstest.domain.repository.FavoriteRepository
import ir.example.newstest.domain.repository.NewsRepository

@Module
abstract class RepositoryBuilder {

    @Binds
    abstract fun bindsNewsRepository(homeRepositoryImpl: NewsRepositoryImpl): NewsRepository

    @Binds
    abstract fun bindsFavoriteRepository(favoriteRepositoryImpl: FavoriteRepositoryImpl): FavoriteRepository

}
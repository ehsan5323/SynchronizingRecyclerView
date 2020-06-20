package ir.example.newstest.di.builder

import ir.example.newstest.data.repository.favorite.FavoriteRepositoryImpl
import ir.example.newstest.data.repository.news.NewsRepositoryImpl
import ir.example.newstest.domain.repository.favorite.FavoriteRepository
import ir.example.newstest.domain.repository.news.NewsRepository
import dagger.Binds
import dagger.Module
import ir.example.newstest.domain.repository.FavoriteRepository
import ir.example.newstest.domain.repository.NewsRepository

@Module
abstract class RepositoryBuilder {

    @Binds
    abstract fun bindsNewsRepository(homeRepositoryImpl: NewsRepositoryImpl): NewsRepository

    @Binds
    abstract fun bindsFavoriteRepository(favoriteRepositoryImpl: FavoriteRepositoryImpl): FavoriteRepository

}
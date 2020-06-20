package ir.example.newstest.di.builder

import com.example.data.repository.favorite.FavoriteRepositoryImpl
import com.example.data.repository.news.NewsRepositoryImpl
import com.example.domain.repository.favorite.FavoriteRepository
import com.example.domain.repository.news.NewsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryBuilder {

    @Binds
    abstract fun bindsNewsRepository(homeRepositoryImpl: NewsRepositoryImpl): NewsRepository

    @Binds
    abstract fun bindsFavoriteRepository(favoriteRepositoryImpl: FavoriteRepositoryImpl): FavoriteRepository

}
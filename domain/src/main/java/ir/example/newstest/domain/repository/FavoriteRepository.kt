package ir.example.newstest.domain.repository

import ir.example.newstest.domain.pojo.Article
import ir.example.newstest.domain.pojo.Detail
import ir.example.newstest.domain.base.Result
import ir.example.newstest.domain.pojo.News
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun getNewsFa(): Flow<Result<List<Detail>>>

    fun addDetail(detail: Detail): Flow<Result<Unit>>

    fun removeDetail(detail: Detail): Flow<Result<Unit>>

    fun favoriteDetail(detail: Detail): Flow<Result<Unit>>

    fun unFavoriteDetail(detail: Detail): Flow<Result<Unit>>


    fun getNewsEn(): Flow<Result<List<Article>>>

    fun addArticle(detail: Article): Flow<Result<Unit>>

    fun removeArticle(detail: Article): Flow<Result<Unit>>

    fun favoriteArticle(detail: Article): Flow<Result<Unit>>

    fun unFavoriteArticle(detail: Article): Flow<Result<Unit>>



    fun addFavorite(news: News): Flow<Result<Unit>>

    fun removeFavorite(news: News): Flow<Result<Unit>>

    fun getNews(): Flow<Result<List<News>>>

}
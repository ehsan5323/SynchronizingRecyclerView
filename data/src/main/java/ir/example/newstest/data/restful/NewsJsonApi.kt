package ir.example.newstest.data.restful

import ir.example.newstest.domain.pojo.NewsEn
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsJsonApi {

    @GET("top-headlines")
    suspend fun getJsonNews(
        @Query("country") sources: String,
        @Query("apiKey") apiKey: String
    ): NewsEn

}
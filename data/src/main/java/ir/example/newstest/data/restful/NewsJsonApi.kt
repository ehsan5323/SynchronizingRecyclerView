package ir.example.newstest.data.restful

import ir.example.newstest.domain.pojo.NewsEn
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val CACHE_MAX_AGE = "CACHE_MAX_AGE"
const val CACHE_MAX_STALE = "CACHE_MAX_STALE"
private const val JSON_LIST_CACHE_MAX_AGE = 30  // 30 seconds
private const val JSON_LIST_CACHE_MAX_STALE = 1 * 24 * 60 * 60   // 1 Days in seconds

interface NewsJsonApi {

    @Headers(
        "$CACHE_MAX_AGE:$JSON_LIST_CACHE_MAX_AGE ",
        "$CACHE_MAX_STALE:$JSON_LIST_CACHE_MAX_STALE"
    )
    @GET("top-headlines")
    suspend fun getJsonNews(
        @Query("country") sources: String,
        @Query("apiKey") apiKey: String
    ): NewsEn

}
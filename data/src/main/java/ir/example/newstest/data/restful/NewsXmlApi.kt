package ir.example.newstest.data.restful

import ir.example.newstest.domain.pojo.NewsFa
import retrofit2.http.GET
import retrofit2.http.Headers

private const val XML_LIST_CACHE_MAX_AGE = 30  // 30 seconds
private const val XML_LIST_CACHE_MAX_STALE = 1 * 24 * 60 * 60   // 1 Days in seconds

interface NewsXmlApi {

    @Headers(
        "$CACHE_MAX_AGE:$XML_LIST_CACHE_MAX_AGE ",
        "$CACHE_MAX_STALE:$XML_LIST_CACHE_MAX_STALE"
    )
    @GET("rss/tp/10")
    suspend fun getXmlNews(): NewsFa

}
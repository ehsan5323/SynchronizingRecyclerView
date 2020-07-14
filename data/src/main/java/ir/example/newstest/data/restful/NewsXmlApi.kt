package ir.example.newstest.data.restful

import ir.example.newstest.domain.pojo.NewsFa
import retrofit2.http.GET
import retrofit2.http.Headers

interface NewsXmlApi {

    @GET("rss/tp/10")
    suspend fun getXmlNews(): NewsFa

}
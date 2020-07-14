package ir.example.newstest.domain.repository

import ir.example.newstest.domain.pojo.NewsEn
import ir.example.newstest.domain.pojo.NewsFa
import ir.example.newstest.domain.pojo.req.XmlNewsReq
import ir.example.newstest.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getXmlNewsFromNetwork(): Flow<Result<NewsFa>>
    fun getJsonNewsFromNetwork(params: XmlNewsReq): Flow<Result<NewsEn>>
}

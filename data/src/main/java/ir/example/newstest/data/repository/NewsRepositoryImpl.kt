package ir.example.newstest.data.repository

import ir.example.newstest.data.restful.NewsJsonApi
import ir.example.newstest.data.restful.NewsXmlApi
import ir.example.newstest.data.util.resultFlow
import ir.example.newstest.domain.pojo.NewsEn
import ir.example.newstest.domain.pojo.NewsFa
import ir.example.newstest.domain.pojo.req.XmlNewsReq
import ir.example.newstest.domain.repository.NewsRepository
import ir.example.newstest.domain.util.FlowListResult
import ir.example.newstest.domain.util.FlowResult
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsJsonApi: NewsJsonApi,
    private val newsXmlApi: NewsXmlApi
) : NewsRepository {

    override fun getXmlNewsFromNetwork(): FlowResult<NewsFa> = resultFlow {
        return@resultFlow newsXmlApi.getXmlNews()
    }

    override fun getJsonNewsFromNetwork(params: XmlNewsReq): FlowResult<NewsEn> =
        resultFlow {
            return@resultFlow newsJsonApi.getJsonNews(params.country, params.apiKey)
        }
}
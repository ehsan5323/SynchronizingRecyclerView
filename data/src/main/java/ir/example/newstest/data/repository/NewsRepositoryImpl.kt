package ir.example.newstest.data.repository

import ir.example.newstest.data.base.resultFlow
import ir.example.newstest.data.restful.NewsJsonApi
import ir.example.newstest.data.restful.NewsXmlApi
import ir.example.newstest.domain.base.Result
import ir.example.newstest.domain.pojo.NewsEn
import ir.example.newstest.domain.pojo.NewsFa
import ir.example.newstest.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsJsonApi: NewsJsonApi,
    private val newsXmlApi: NewsXmlApi
) : NewsRepository {

    override fun getXmlNewsFromNetwork(): Flow<Result<NewsFa>> = resultFlow {
        return@resultFlow newsXmlApi.getXmlNews()
    }

    override fun getJsonNewsFromNetwork(params: Pair<String, String>): Flow<Result<NewsEn>> =
        resultFlow {
            return@resultFlow newsJsonApi.getJsonNews(params.first, params.second)
        }

}
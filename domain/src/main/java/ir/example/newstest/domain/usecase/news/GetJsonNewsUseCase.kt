package ir.example.newstest.domain.usecase.news

import ir.example.newstest.domain.base.BaseUseCase
import ir.example.newstest.domain.pojo.Article
import ir.example.newstest.domain.pojo.NewsEn
import ir.example.newstest.domain.pojo.req.XmlNewsReq
import ir.example.newstest.domain.repository.NewsRepository
import ir.example.newstest.domain.util.FlowListResult
import ir.example.newstest.domain.util.Result
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetJsonNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseUseCase<XmlNewsReq, List<Article>>() {

    override fun invoke(params: XmlNewsReq): FlowListResult<Article> =
        newsRepository.getJsonNewsFromNetwork(params)
            .map { newsEn ->
                when (newsEn) {
                    is Result.Success -> {
                        val articles = newsEn.data.articles.toMutableList()
//                                articles += articles + articles + articles + articles + articles + articles
                        Result.Success(articles)
                    }
                    is Result.Error -> Result.Error(newsEn.error)
                    is Result.Loading -> Result.Loading
                }
            }
}
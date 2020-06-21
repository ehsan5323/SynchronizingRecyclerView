package ir.example.newstest.domain.usecase.news

import ir.example.newstest.domain.base.FlowListResult
import ir.example.newstest.domain.base.Result
import ir.example.newstest.domain.pojo.Article
import ir.example.newstest.domain.pojo.ArticleFavorite
import ir.example.newstest.domain.pojo.NewsEn
import ir.example.newstest.domain.pojo.XmlNewsReq
import ir.example.newstest.domain.repository.NewsRepository
import ir.example.newstest.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class JsonNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseUseCase<XmlNewsReq, List<Article>>() {

    override fun invoke(params: XmlNewsReq): FlowListResult<Article> {
        return newsRepository.getJsonNewsFromNetwork(params)
            .combine(newsRepository.getArticleFlows()) { newsEn: Result<NewsEn>, favorites: Result<List<ArticleFavorite>> ->
                when (favorites) {
                    is Result.Success -> {
                        when (newsEn) {
                            is Result.Success -> {
                                Result.Success(newsEn.data.articles.map { article ->
                                    article.copy(isFavorite = favorites.data.filter { it.link == article.link }
                                        .isNullOrEmpty().not())
                                })
                            }
                            is Result.Error -> Result.Error(newsEn.error)
                            is Result.Loading -> Result.Loading
                        }
                    }
                    is Result.Error -> Result.Error(favorites.error)
                    is Result.Loading -> Result.Loading
                }
            }
    }

}
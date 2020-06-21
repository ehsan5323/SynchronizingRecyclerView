package ir.example.newstest.domain.usecase.news

import ir.example.newstest.domain.base.FlowListResult
import ir.example.newstest.domain.base.Result
import ir.example.newstest.domain.pojo.Article
import ir.example.newstest.domain.pojo.ArticleFavorite
import ir.example.newstest.domain.pojo.NewsEn
import ir.example.newstest.domain.pojo.XmlNewsReq
import ir.example.newstest.domain.repository.NewsRepository
import ir.example.newstest.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class JsonNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseUseCase<XmlNewsReq, List<Article>>() {
    override fun invoke(params: XmlNewsReq): FlowListResult<Article> {
        return newsRepository.getJsonNewsFromNetwork(params)/*.map {
            when (it) {
                is Result.Success -> {
                    Result.Success(it.data.articles.map { article ->
                        article.copy(isFavorite = false)
                    })
                }
                is Result.Error -> Result.Error(it.error)
                is Result.Loading -> Result.Loading
            }
        }*/
            .combine(newsRepository.getArticleFlows()) { newsEn: Result<NewsEn>, favorites: Result<List<ArticleFavorite>> ->
                when(favorites) {
                    is Result.Success -> {
                        when (newsEn) {
                            is Result.Success -> {
                                Result.Success(newsEn.data.articles.map { article ->
                                    article.copy(isFavorite = favorites.data.filter { it.link == article.link }.isNullOrEmpty().not())
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

fun getX(): Flow<Set<String>> = flow {
    emit(
        hashSetOf(
            "https://www.nytimes.com/2020/06/20/us/minnesota-police-george-floyd.html",
            "https://9to5mac.com/2020/06/20/wwdc-report-ios-14-mac-arm/",
            "https://www.foxnews.com/sports/clemson-athletics-28-positive-coronavirus-cases",
            "https://nypost.com/2020/06/20/brazil-tops-1-million-coronavirus-cases-second-only-to-us/"
        )
    )
}
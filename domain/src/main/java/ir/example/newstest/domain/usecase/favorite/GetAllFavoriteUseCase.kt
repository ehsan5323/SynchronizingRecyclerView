package ir.example.newstest.domain.usecase.favorite

import ir.example.newstest.domain.base.FlowListResult
import ir.example.newstest.domain.base.Result
import ir.example.newstest.domain.pojo.*
import ir.example.newstest.domain.repository.NewsRepository
import ir.example.newstest.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetAllFavoriteUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseUseCase<XmlNewsReq, List<News>>() {
    override fun invoke(params: XmlNewsReq): FlowListResult<News> {
        return combine(
            newsRepository.getXmlNewsFromNetwork(),
            newsRepository.getJsonNewsFromNetwork(params),
            newsRepository.getArticleFlows(),
            newsRepository.getDetailFlows()
        )
        { xmlNewsResult: Result<NewsFa>, jsonNewsResult: Result<NewsEn>, articleFavorite: Result<List<ArticleFavorite>>, detailFavorite: Result<List<DetailFavorite>> ->
            when (xmlNewsResult) {
                is Result.Success -> {
                    when (jsonNewsResult) {
                        is Result.Success -> {
                            when (articleFavorite) {
                                is Result.Success -> {
                                    when (detailFavorite) {
                                        is Result.Success -> {
                                            val list = mutableListOf<News>()
                                            val details = xmlNewsResult.data.channel?.details
                                                ?: mutableListOf()

//                                            val aaaa= details.filter { detail ->
//                                                detail.guid == detailFavorite.data.forEach {  }
//                                                detailFavorite.data.filter { database->
//                                                    detail.guid==database.link }
//                                            }

                                            detailFavorite.data.forEach { db ->
                                                details.forEach { detail ->
                                                    if (db.link.equals(detail.guid))
                                                        list.add(detail)

                                                }

//                                                list.add(details.first { detail ->
//                                                    detail.equals(db.link)
//                                                })
                                            }



                                            articleFavorite.data.forEach { db ->
                                                jsonNewsResult.data.articles.forEach { article ->
                                                    if (db.link.equals(article.link))
                                                        list.add(article)
                                                }
                                            }

//                                            list.addAll(
//                                                details.map { detail->
//                                                    detail.copy(isFavorite = detailFavorite.data.filter { it.link == detail.guid }.isNullOrEmpty().not())
//                                                }
//                                            )
//                                            list.addAll(jsonNewsResult.data.articles.map { article ->
//                                                article.copy(isFavorite = articleFavorite.data.filter { it.link == article.link }.isNullOrEmpty().not())
//                                            })
                                            Result.Success(list)

                                        }

                                        is Result.Error -> Result.Error(detailFavorite.error)
                                        is Result.Loading -> Result.Loading
                                    }
                                }
                                is Result.Error -> Result.Error(articleFavorite.error)
                                is Result.Loading -> Result.Loading
                            }
                        }
                        is Result.Error -> Result.Error(jsonNewsResult.error)
                        is Result.Loading -> Result.Loading
                    }
                }
                is Result.Error -> Result.Error(xmlNewsResult.error)
                is Result.Loading -> Result.Loading
            }
        }
    }
}

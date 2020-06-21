package ir.example.newstest.data.repository

import ir.example.newstest.data.base.resultFlow
import ir.example.newstest.data.restful.NewsJsonApi
import ir.example.newstest.data.restful.NewsXmlApi
import ir.example.newstest.data.source.db.dao.FavoriteDao
import ir.example.newstest.domain.base.FlowListResult
import ir.example.newstest.domain.base.FlowResult
import ir.example.newstest.domain.pojo.*
import ir.example.newstest.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsJsonApi: NewsJsonApi,
    private val newsXmlApi: NewsXmlApi,
    private val favoriteDao: FavoriteDao
) : NewsRepository {

    override fun getXmlNewsFromNetwork(): FlowResult<NewsFa> = resultFlow {
        return@resultFlow newsXmlApi.getXmlNews()
    }

    override fun getJsonNewsFromNetwork(params: XmlNewsReq): FlowResult<NewsEn> =
        resultFlow {
            return@resultFlow newsJsonApi.getJsonNews(params.country, params.apiKey)
        }

    override fun addArticleFavorite(articleFavorite: ArticleFavorite): FlowResult<Unit> =
        resultFlow {
            favoriteDao.addArticleFavorite(articleFavorite)
        }

    override fun deleteArticleFavorite(articleFavorite: ArticleFavorite): FlowResult<Unit> =
        resultFlow {
            favoriteDao.deleteArticleFavorite(articleFavorite)
        }

    override fun getArticleFlows(): FlowListResult<ArticleFavorite> =
        favoriteDao.getArticleFavorites().resultFlow()


    override fun addDetailFavorite(detailFavorite: DetailFavorite): FlowResult<Unit> =
        resultFlow {
            favoriteDao.addDetailFavorite(detailFavorite)
        }


    override fun deleteDetailFavorite(detailFavorite: DetailFavorite): FlowResult<Unit> =
        resultFlow {
            favoriteDao.deleteDetailFavorite(detailFavorite)
        }

    override fun getDetailFlows(): FlowListResult<DetailFavorite> =
        favoriteDao.getDetailFlows().resultFlow()

}
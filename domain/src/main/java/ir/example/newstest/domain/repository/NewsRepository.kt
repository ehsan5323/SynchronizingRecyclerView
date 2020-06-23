package ir.example.newstest.domain.repository

import ir.example.newstest.domain.pojo.ArticleFavorite
import ir.example.newstest.domain.pojo.DetailFavorite
import ir.example.newstest.domain.pojo.NewsEn
import ir.example.newstest.domain.pojo.NewsFa
import ir.example.newstest.domain.pojo.req.XmlNewsReq
import ir.example.newstest.domain.util.FlowListResult
import ir.example.newstest.domain.util.FlowResult
import ir.example.newstest.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getXmlNewsFromNetwork(): Flow<Result<NewsFa>>
    fun getJsonNewsFromNetwork(params: XmlNewsReq): Flow<Result<NewsEn>>

    fun addArticleFavorite(articleFavorite: ArticleFavorite): FlowResult<Unit>
    fun deleteArticleFavorite(articleFavorite: ArticleFavorite): FlowResult<Unit>
    fun getArticleFlows(): FlowListResult<ArticleFavorite>

    fun addDetailFavorite(detailFavorite: DetailFavorite): FlowResult<Unit>
    fun deleteDetailFavorite(detailFavorite: DetailFavorite): FlowResult<Unit>
    fun getDetailFlows(): FlowListResult<DetailFavorite>
}

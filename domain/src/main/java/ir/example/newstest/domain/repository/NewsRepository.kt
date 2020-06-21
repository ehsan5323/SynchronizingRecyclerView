package ir.example.newstest.domain.repository

import ir.example.newstest.domain.base.FlowListResult
import ir.example.newstest.domain.base.FlowResult
import kotlinx.coroutines.flow.Flow
import ir.example.newstest.domain.base.Result
import ir.example.newstest.domain.pojo.*

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

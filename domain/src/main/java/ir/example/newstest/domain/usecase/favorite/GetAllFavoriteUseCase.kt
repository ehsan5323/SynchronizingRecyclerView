package ir.example.newstest.domain.usecase.favorite

import ir.example.newstest.domain.base.BaseUseCase
import ir.example.newstest.domain.error.ApiException
import ir.example.newstest.domain.error.ErrorType
import ir.example.newstest.domain.pojo.Article
import ir.example.newstest.domain.pojo.Detail
import ir.example.newstest.domain.pojo.News
import ir.example.newstest.domain.pojo.req.XmlNewsReq
import ir.example.newstest.domain.usecase.news.GetJsonNewsUseCase
import ir.example.newstest.domain.usecase.news.GetXmlNewsUseCase
import ir.example.newstest.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetAllFavoriteUseCase @Inject constructor(
    private val getXmlNewsUseCase: GetXmlNewsUseCase,
    private val getJsonNewsUseCase: GetJsonNewsUseCase
) : BaseUseCase<XmlNewsReq, List<News>>() {

    override fun invoke(params: XmlNewsReq): Flow<Result<List<News>>> =
        getXmlNewsUseCase(Unit)
            .combine(getJsonNewsUseCase(params)) { favoriteXml: Result<List<Detail>>, favoriteJson: Result<List<Article>> ->
                when (favoriteXml) {
                    is Result.Success -> {
                        when (favoriteJson) {
                            is Result.Success -> {
                                val list = mutableListOf<News>()
                                list.addAll(favoriteXml.data.filter {
                                    it.isFavorite == true
                                })
                                list.addAll(favoriteJson.data.filter {
                                    it.isFavorite == true
                                })
                                Result.Success(list)
                            }
                            is Result.Error -> Result.Error(ApiException("", ErrorType.UNKNOWN, 1))
                            is Result.Loading -> Result.Loading
                        }
                    }
                    is Result.Error -> Result.Error(ApiException("", ErrorType.UNKNOWN, 1))
                    is Result.Loading -> Result.Loading
                }
            }
}

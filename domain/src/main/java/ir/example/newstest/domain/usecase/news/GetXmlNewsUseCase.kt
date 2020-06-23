package ir.example.newstest.domain.usecase.news

import ir.example.newstest.domain.base.BaseUseCase
import ir.example.newstest.domain.pojo.Detail
import ir.example.newstest.domain.pojo.DetailFavorite
import ir.example.newstest.domain.pojo.NewsFa
import ir.example.newstest.domain.repository.NewsRepository
import ir.example.newstest.domain.util.FlowListResult
import ir.example.newstest.domain.util.Result
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetXmlNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseUseCase<Unit, List<Detail>>() {

    override fun invoke(params: Unit): FlowListResult<Detail> =
        newsRepository.getXmlNewsFromNetwork()
            .combine(newsRepository.getDetailFlows()) { newsFa: Result<NewsFa>, favorites: Result<List<DetailFavorite>> ->
                when (favorites) {
                    is Result.Success -> {
                        when (newsFa) {
                            is Result.Success -> {
                                val details = newsFa.data.channel?.details ?: mutableListOf()
                                Result.Success(details.map { detail ->
                                    detail.copy(isFavorite = favorites.data.filter { it.link == detail.guid }
                                        .isNullOrEmpty().not())
                                })
                            }
                            is Result.Error -> Result.Error(newsFa.error)
                            is Result.Loading -> Result.Loading
                        }
                    }
                    is Result.Error -> Result.Error(favorites.error)
                    is Result.Loading -> Result.Loading
                }
            }
}
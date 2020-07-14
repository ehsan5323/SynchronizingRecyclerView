package ir.example.newstest.domain.usecase.news

import ir.example.newstest.domain.base.BaseUseCase
import ir.example.newstest.domain.pojo.Detail
import ir.example.newstest.domain.pojo.NewsFa
import ir.example.newstest.domain.repository.NewsRepository
import ir.example.newstest.domain.util.FlowListResult
import ir.example.newstest.domain.util.Result
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetXmlNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseUseCase<Unit, List<Detail>>() {

    override fun invoke(params: Unit): FlowListResult<Detail> =
        newsRepository.getXmlNewsFromNetwork()
            .map { newsFa ->
                when (newsFa) {
                    is Result.Success -> {
                        val details = newsFa.data.channel?.details ?: mutableListOf()
                        Result.Success(details)
                    }
                    is Result.Error -> Result.Error(newsFa.error)
                    is Result.Loading -> Result.Loading
                }

            }
}
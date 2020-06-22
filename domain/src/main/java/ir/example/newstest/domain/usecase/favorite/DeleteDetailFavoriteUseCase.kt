package ir.example.newstest.domain.usecase.favorite

import ir.example.newstest.domain.base.FlowResult
import ir.example.newstest.domain.pojo.DetailFavorite
import ir.example.newstest.domain.repository.NewsRepository
import ir.example.newstest.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class DeleteDetailFavoriteUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseUseCase<String, Unit>() {

    override fun invoke(params: String): FlowResult<Unit> =
        newsRepository.deleteDetailFavorite(DetailFavorite(params))
}
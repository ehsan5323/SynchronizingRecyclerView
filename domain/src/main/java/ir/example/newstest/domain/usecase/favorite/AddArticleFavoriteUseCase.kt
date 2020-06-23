package ir.example.newstest.domain.usecase.favorite

import ir.example.newstest.domain.base.BaseUseCase
import ir.example.newstest.domain.pojo.ArticleFavorite
import ir.example.newstest.domain.repository.NewsRepository
import ir.example.newstest.domain.util.FlowResult
import javax.inject.Inject

class AddArticleFavoriteUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseUseCase<String, Unit>() {

    override fun invoke(params: String): FlowResult<Unit> =
        newsRepository.addArticleFavorite(ArticleFavorite(params))
}
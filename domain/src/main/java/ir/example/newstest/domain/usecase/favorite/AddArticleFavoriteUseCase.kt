package ir.example.newstest.domain.usecase.favorite

import ir.example.newstest.domain.base.FlowResult
import ir.example.newstest.domain.pojo.ArticleFavorite
import ir.example.newstest.domain.repository.NewsRepository
import ir.example.newstest.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class AddArticleFavoriteUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseUseCase<String, Unit>() {

    override fun invoke(params: String): FlowResult<Unit> {
        return newsRepository.addArticleFavorite(ArticleFavorite(params))
    }
}
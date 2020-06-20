package ir.example.newstest.domain.usecase.favorite.json

import ir.example.newstest.domain.base.Result
import ir.example.newstest.domain.pojo.Article
import ir.example.newstest.domain.repository.FavoriteRepository
import ir.example.newstest.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class unFavoriteNewsEn @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseUseCase<Article, Unit>() {

    override fun invoke(params: Article): Flow<Result<Unit>> =
        favoriteRepository.favoriteArticle(params)

}
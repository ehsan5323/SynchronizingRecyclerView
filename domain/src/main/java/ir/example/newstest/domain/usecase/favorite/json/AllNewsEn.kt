package ir.example.newstest.domain.usecase.favorite.json

import ir.example.newstest.domain.base.Result
import ir.example.newstest.domain.pojo.Article
import ir.example.newstest.domain.repository.FavoriteRepository
import ir.example.newstest.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AllNewsEn @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseUseCase<Unit, List<Article>>() {

    override fun invoke(params: Unit): Flow<Result<List<Article>>> =
        favoriteRepository.getNewsEn()

}
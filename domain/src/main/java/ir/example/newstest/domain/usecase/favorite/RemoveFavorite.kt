package ir.example.newstest.domain.usecase.favorite

import ir.example.newstest.domain.base.Result
import ir.example.newstest.domain.pojo.News
import ir.example.newstest.domain.repository.FavoriteRepository
import ir.example.newstest.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveFavorite @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseUseCase<News, Unit>() {

    override fun invoke(params: News): Flow<Result<Unit>> =
        favoriteRepository.removeFavorite(params)

}
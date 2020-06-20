package ir.example.newstest.domain.usecase.favorite.xml

import ir.example.newstest.domain.base.Result
import ir.example.newstest.domain.pojo.Detail
import ir.example.newstest.domain.repository.FavoriteRepository
import ir.example.newstest.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class favoriteNewsFa @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseUseCase<Detail, Unit>() {

    override fun invoke(params: Detail): Flow<Result<Unit>> =
        favoriteRepository.favoriteDetail(params)

}
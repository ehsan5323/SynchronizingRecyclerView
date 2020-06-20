package ir.example.newstest.domain.usecase.favorite.xml

import ir.example.newstest.domain.base.Result
import ir.example.newstest.domain.pojo.Detail
import ir.example.newstest.domain.repository.FavoriteRepository
import ir.example.newstest.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AllNewsFa @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseUseCase<Unit, List<Detail>>() {

    override fun invoke(params: Unit): Flow<Result<List<Detail>>> =
        favoriteRepository.getNewsFa()

}
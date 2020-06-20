package ir.example.newstest.domain.usecase.favorite

import ir.example.newstest.domain.base.Result
import ir.example.newstest.domain.pojo.News
import ir.example.newstest.domain.repository.FavoriteRepository
import ir.example.newstest.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AllNews @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseUseCase<Unit, List<News>>() {

    override fun invoke(params: Unit): Flow<Result<List<News>>> =
        favoriteRepository.getNews()

}
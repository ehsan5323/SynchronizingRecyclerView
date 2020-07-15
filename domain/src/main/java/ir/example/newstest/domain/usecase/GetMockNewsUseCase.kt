package ir.example.newstest.domain.usecase

import ir.example.newstest.domain.base.BaseUseCase
import ir.example.newstest.domain.pojo.News
import ir.example.newstest.domain.repository.NewsRepository
import javax.inject.Inject

class GetMockNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseUseCase<Unit, MutableList<News>>() {

    override fun invoke(params: Unit): MutableList<News> =
        newsRepository.getMockNews()
}
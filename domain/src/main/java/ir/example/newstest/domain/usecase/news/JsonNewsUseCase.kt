package ir.example.newstest.domain.usecase.news

import ir.example.newstest.domain.pojo.NewsEn
import ir.example.newstest.domain.base.Result
import ir.example.newstest.domain.repository.NewsRepository
import ir.example.newstest.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class JsonNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseUseCase<Pair<String, String>, NewsEn>() {
    override fun invoke(params: Pair<String, String>): Flow<Result<NewsEn>> =
        newsRepository.getJsonNewsFromNetwork(params)

}
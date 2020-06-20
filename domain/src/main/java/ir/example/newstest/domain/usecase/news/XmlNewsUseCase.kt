package ir.example.newstest.domain.usecase.news

import ir.example.newstest.domain.base.Result
import ir.example.newstest.domain.pojo.NewsFa
import ir.example.newstest.domain.repository.NewsRepository
import ir.example.newstest.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class XmlNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseUseCase<Unit, NewsFa>() {

    override fun invoke(params: Unit): Flow<Result<NewsFa>> = newsRepository.getXmlNewsFromNetwork()

}
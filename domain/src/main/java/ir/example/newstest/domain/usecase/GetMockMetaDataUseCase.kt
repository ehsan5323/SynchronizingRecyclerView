package ir.example.newstest.domain.usecase

import ir.example.newstest.domain.base.BaseUseCase
import ir.example.newstest.domain.pojo.MetaData
import ir.example.newstest.domain.pojo.News
import ir.example.newstest.domain.pojo.NewsEn
import ir.example.newstest.domain.repository.NewsRepository
import javax.inject.Inject

class GetMockMetaDataUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseUseCase<Unit, MutableList<MetaData>>() {

    override fun invoke(params: Unit): MutableList<MetaData> =
        newsRepository.getMockMetaData()
}
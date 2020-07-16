package ir.example.newstest.domain.repository

import ir.example.newstest.domain.pojo.MetaData
import ir.example.newstest.domain.pojo.News

interface NewsRepository {
    fun getMockNews(): MutableList<News>
    fun getMockMetaData(): MutableList<MetaData>
}

package ir.example.newstest.data.repository

import ir.example.newstest.data.base.resultFlow
import ir.example.newstest.data.source.db.dao.NewsDao
import ir.example.newstest.domain.base.Result
import ir.example.newstest.domain.pojo.Article
import ir.example.newstest.domain.pojo.Detail
import ir.example.newstest.domain.pojo.News
import ir.example.newstest.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val newsDao: NewsDao
) : FavoriteRepository {

    override fun getNewsFa(): Flow<Result<List<Detail>>> = resultFlow {
        return@resultFlow newsDao.getFavoriteNewsFa()
    }

    override fun addDetail(detail: Detail) = resultFlow {
        return@resultFlow newsDao.addDetail(detail)
    }

    override fun removeDetail(detail: Detail) = resultFlow {
        return@resultFlow newsDao.removeDetail(detail)
    }

    override fun favoriteDetail(detail: Detail) = resultFlow {
        return@resultFlow newsDao.favoriteNewsFa(detail.guid)
    }

    override fun unFavoriteDetail(detail: Detail) = resultFlow {
        return@resultFlow newsDao.unFavoriteNewsFa(detail.guid)
    }


    override fun getNewsEn(): Flow<Result<List<Article>>> = resultFlow {
        return@resultFlow newsDao.getFavoriteNewsEn()
    }

    override fun addArticle(detail: Article) = resultFlow {
        return@resultFlow newsDao.addArticle(detail)
    }

    override fun removeArticle(detail: Article) = resultFlow {
        return@resultFlow newsDao.removeArticle(detail)
    }

    override fun favoriteArticle(detail: Article) = resultFlow {
        return@resultFlow newsDao.favoriteNewsEn(detail.link)
    }

    override fun unFavoriteArticle(detail: Article) = resultFlow {
        return@resultFlow newsDao.unFavoriteNewsEn(detail.link)
    }


    override fun addFavorite(news: News) = resultFlow {
        when (news) {
            is Detail -> {
                newsDao.addDetail(news)
            }
            is Article -> {
                newsDao.addArticle(news)
            }
        }
    }

    override fun removeFavorite(news: News) = resultFlow {
        when (news) {
            is Detail -> {
                newsDao.removeDetail(news)
            }
            is Article -> {
                newsDao.removeArticle(news)
            }
        }
    }

    override fun getNews(): Flow<Result<List<News>>> = resultFlow {
        val list = mutableListOf<News>()
        list.addAll(newsDao.getFavoriteNewsEn())
        list.addAll(newsDao.getFavoriteNewsFa())
        return@resultFlow list
    }
}
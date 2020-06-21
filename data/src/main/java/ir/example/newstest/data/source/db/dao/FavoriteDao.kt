package ir.example.newstest.data.source.db.dao

import androidx.room.*
import ir.example.newstest.domain.pojo.ArticleFavorite
import ir.example.newstest.domain.pojo.DetailFavorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticleFavorite(articleFavorite: ArticleFavorite)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDetailFavorite(detailFavorite: DetailFavorite)

    @Delete
    suspend fun deleteArticleFavorite(articleFavorite: ArticleFavorite)

    @Delete
    suspend fun deleteDetailFavorite(detailFavorite: DetailFavorite)

    @Query("SELECT * FROM article_favorite")
    fun getArticleFavorites(): Flow<List<ArticleFavorite>>

    @Query("SELECT * FROM detail_favorite")
    fun getDetailFlows(): Flow<List<DetailFavorite>>

}
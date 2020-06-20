package ir.example.newstest.data.source.db.dao

import androidx.room.*
import ir.example.newstest.domain.pojo.Article
import ir.example.newstest.domain.pojo.Detail

@Dao
interface NewsDao {

    @Query("SELECT * FROM news_fa")
    suspend fun getFavoriteNewsFa(): List<Detail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDetail(detail: Detail)

    @Delete
    suspend fun removeDetail(detail: Detail)


    @Query("UPDATE news_fa SET isFavorite=1 WHERE guid =:id")
    suspend fun favoriteNewsFa(id: String)

    @Query("UPDATE news_fa SET isFavorite=0 WHERE guid =:id")
    suspend fun unFavoriteNewsFa(id: String)


//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun upsertNewsFaDetail(details: List<Detail>)


    /*json - news_en*/


    @Query("SELECT * FROM news_en")
    suspend fun getFavoriteNewsEn(): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticle(article: Article)

    @Delete
    suspend fun removeArticle(article: Article)

    @Query("UPDATE news_en SET isFavorite=1 WHERE url =:id")
    suspend fun favoriteNewsEn(id: String)

    @Query("UPDATE news_en SET isFavorite=0 WHERE url =:id")
    suspend fun unFavoriteNewsEn(id: String)




    //////////////////








//
//    @Query("SELECT * FROM news_fa")
//    fun getNewsFaDetail(): LiveData<List<Detail>>
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun upsertNewsFaDetail(details: List<Detail>)

//    @Query("UPDATE news_fa SET isFavorite=1 WHERE guid =:id")
//    suspend fun favoriteNewsFa(id: String)
//
//    @Query("UPDATE news_fa SET isFavorite=0 WHERE guid =:id")
//    suspend fun unFavoriteNewsFa(id: String)

//    @Query("SELECT * FROM news_fa WHERE guid=:id")
//    suspend fun findNewsFaById(id: String): Detail?







//    @Query("SELECT * FROM news_en")
//    fun getNewsEnDetail(): LiveData<List<Article>>
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun upsertNewsEnDetail(details: List<Article>)

//    @Query("UPDATE news_en SET isFavorite=1 WHERE url =:id")
//    suspend fun favoriteNewsEn(id: String)
//
//    @Query("UPDATE news_en SET isFavorite=0 WHERE url =:id")
//    suspend fun unFavoriteNewsEn(id: String)

//    @Query("SELECT * FROM news_en WHERE isFavorite=1")
//    fun getFavoriteNewsEn(): LiveData<List<Article>>

//    @Query("SELECT * FROM news_en WHERE url=:id")
//    suspend fun findNewsEnById(id: String): Article?


}
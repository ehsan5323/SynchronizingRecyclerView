package ir.example.newstest.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.example.newstest.data.source.db.dao.FavoriteDao
import ir.example.newstest.domain.pojo.ArticleFavorite
import ir.example.newstest.domain.pojo.DetailFavorite

@Database(
    entities = [
        ArticleFavorite::class,
        DetailFavorite::class
    ],
    version = AppDataBase.VERSION,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        const val DB_NAME = "TestAppDB"
        const val VERSION = 1
    }

    abstract fun favoriteDao(): FavoriteDao

}

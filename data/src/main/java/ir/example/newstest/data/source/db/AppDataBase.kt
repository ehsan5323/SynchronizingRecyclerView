package ir.example.newstest.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.example.newstest.data.source.db.dao.NewsDao
import ir.example.newstest.domain.pojo.Article
import ir.example.newstest.domain.pojo.Detail

@Database(
    entities = [
        Detail::class,
        Article::class
    ],
    version = AppDataBase.VERSION,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        const val DB_NAME = "TestAppDB"
        const val VERSION = 1
    }

    abstract fun newsDao(): NewsDao

}
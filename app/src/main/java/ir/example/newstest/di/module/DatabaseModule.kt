package ir.example.newstest.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ir.example.newstest.application.NewsTestApp
import ir.example.newstest.data.source.db.AppDataBase
import ir.example.newstest.data.source.db.dao.FavoriteDao
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: NewsTestApp): AppDataBase {
        return Room
            .databaseBuilder(application, AppDataBase::class.java, AppDataBase.DB_NAME)
            .build()
    }

    @Provides
    fun provideFavoriteDao(db: AppDataBase): FavoriteDao {
        return db.favoriteDao()
    }

}
package ir.example.newstest.domain.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_favorite")
data class ArticleFavorite(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "link")
    val link: String
)


@Entity(tableName = "detail_favorite")
data class DetailFavorite(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "guid")
    val link: String
)
package ir.example.newstest.domain.pojo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class NewsEn(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResult: Int,
    @SerializedName("articles")
    val articles: List<Article>
)

@Parcelize
data class Article(
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    override val title: String,
    @SerializedName("description")
    override val description: String?,
    @SerializedName("url")
    override val link: String,
    @SerializedName("urlToImage")
    val image: String?,
    @SerializedName("publishedAt")
    override val date: String,
    @SerializedName("content")
    val content: String?,
    override var isFavorite: Boolean?
) : Parcelable, News
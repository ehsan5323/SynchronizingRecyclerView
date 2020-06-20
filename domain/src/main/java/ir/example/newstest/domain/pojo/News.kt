package ir.example.newstest.domain.pojo

interface News {
    val title : String?
    val description : String?
    val date : String?
    val link : String?
    val isFavorite : Boolean?
}

enum class NewsType {
    ARTICLE, DETAIL
}
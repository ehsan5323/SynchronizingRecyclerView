package ir.example.newstest.domain.pojo

interface News {
    val title : String?
    val description : String?
    val seasons: Season?
    val link : String?
}

enum class NewsType {
    ARTICLE, DETAIL
}
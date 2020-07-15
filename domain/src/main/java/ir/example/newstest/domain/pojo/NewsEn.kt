package ir.example.newstest.domain.pojo

data class NewsEn(
    val author: String? = null,
    override val title: String,
    override val description: String,
    override val link: String,
    val image: String? = null,
    override val seasons: Season,
    val content: String? = null
) : News
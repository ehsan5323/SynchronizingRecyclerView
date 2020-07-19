package ir.example.newstest.domain.pojo

data class NewsFa(
    override val title: String? = null,
    override val link: String? = null,
    override val description: String? = null,
    val imageUrl: String? = null,
    val category: String? = null,
    override val seasons: Season,
    val guid: String = ""
) : News
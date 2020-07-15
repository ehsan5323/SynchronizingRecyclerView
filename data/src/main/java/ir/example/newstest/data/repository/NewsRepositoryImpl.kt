package ir.example.newstest.data.repository

import ir.example.newstest.domain.pojo.*
import ir.example.newstest.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor() : NewsRepository {

    override fun getMockNews(): MutableList<News> = createMockNewsFaMockList()

    override fun getMockMetaData(): MutableList<MetaData> = createMockMetaData()

    private fun createMockNewsFaMockList(): MutableList<News> {
        return mutableListOf(

            NewsFa("title1", "link", "description1 description1 description1 description1 description1 description1 description1", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.SPRING, guid = "asdf"),
            NewsEn("title1", "title1", "description1 description1 description1 description1 description1 description1 description1", "link", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",Season.SPRING,"asdf"),
            NewsFa("title2", "link", "description1 description1 description1 description1 description1 description1 description1", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.SPRING, guid = "https://homepages.cae.wisc.edu/~ece533/images/baboon.png"),
            NewsEn("title2", "title2", "description1 description1 description1 description1 description1 description1 description1", "link", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.SPRING),
            NewsFa("title3", "link", "description1 description1 description1 description1 description1 description1 description1", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.SPRING, guid = "https://homepages.cae.wisc.edu/~ece533/images/boat.png"),
            NewsFa("title3", "link", "description1 description1 description1 description1 description1 description1 description1", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.SPRING, guid = "https://homepages.cae.wisc.edu/~ece533/images/boat.png"),
            NewsFa("title3", "link", "description1 description1 description1 description1 description1 description1 description1", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.SPRING, guid = "https://homepages.cae.wisc.edu/~ece533/images/boat.png"),
            NewsFa("title3", "link", "description1 description1 description1 description1 description1 description1 description1", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.SPRING, guid = "https://homepages.cae.wisc.edu/~ece533/images/boat.png"),
            NewsEn("title3", "title3", "description1 description1 description1 description1 description1 description1 description1", "link", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.SPRING),
            NewsEn("title5", "title5", "description1 description1 description1 description1 description1 description1 description1", "link", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.SPRING),



            NewsEn("title4", "title4", "description1 description1 description1 description1 description1 description1 description1", "link", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.SUMMER),
            NewsFa("title4", "link", "description1 description1 description1 description1 description1 description1 description1", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.SUMMER, guid = "https://homepages.cae.wisc.edu/~ece533/images/cat.png"),
            NewsFa("title5", "link", "description1 description1 description1 description1 description1 description1 description1", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.SUMMER, guid = "https://homepages.cae.wisc.edu/~ece533/images/fruits.png"),


            NewsFa("title4", "link", "description1 description1 description1 description1 description1 description1 description1", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.FALL, guid = "https://homepages.cae.wisc.edu/~ece533/images/cat.png"),
            NewsFa("title5", "link", "description1 description1 description1 description1 description1 description1 description1", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.FALL, guid = "https://homepages.cae.wisc.edu/~ece533/images/fruits.png"),
            NewsEn("title4", "title4", "description1 description1 description1 description1 description1 description1 description1", "link", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.FALL),
            NewsFa("title5", "link", "description1 description1 description1 description1 description1 description1 description1", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.FALL, guid = "https://homepages.cae.wisc.edu/~ece533/images/fruits.png"),
            NewsFa("title4", "link", "description1 description1 description1 description1 description1 description1 description1", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.FALL, guid = "https://homepages.cae.wisc.edu/~ece533/images/cat.png"),
            NewsEn("title4", "title4", "description1 description1 description1 description1 description1 description1 description1", "link", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.FALL),
            NewsFa("title4", "link", "description1 description1 description1 description1 description1 description1 description1", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.FALL, guid = "https://homepages.cae.wisc.edu/~ece533/images/cat.png"),
            NewsEn("title4", "title4", "description1 description1 description1 description1 description1 description1 description1", "link", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.FALL),
            NewsEn("title4", "title4", "description1 description1 description1 description1 description1 description1 description1", "link", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.FALL),
            NewsEn("title4", "title4", "description1 description1 description1 description1 description1 description1 description1", "link", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.FALL),



            NewsEn("title1", "title1", "description1 description1 description1 description1 description1 description1 description1", "link", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.WINTER),
            NewsEn("title2", "title2", "description1 description1 description1 description1 description1 description1 description1", "link", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.WINTER),
            NewsEn("title3", "title3", "description1 description1 description1 description1 description1 description1 description1", "link", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.WINTER),
            NewsFa("title4", "link", "description1 description1 description1 description1 description1 description1 description1", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.WINTER, guid = "https://homepages.cae.wisc.edu/~ece533/images/fruits.png"),
            NewsFa("title5", "link", "description1 description1 description1 description1 description1 description1 description1", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.WINTER, guid = "https://homepages.cae.wisc.edu/~ece533/images/cat.png"),
            NewsEn("title6", "title6", "description1 description1 description1 description1 description1 description1 description1", "link", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.WINTER),
            NewsFa("title7", "link", "description1 description1 description1 description1 description1 description1 description1", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.WINTER, guid = "https://homepages.cae.wisc.edu/~ece533/images/cat.png"),
            NewsEn("title8", "title8", "description1 description1 description1 description1 description1 description1 description1", "link", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.WINTER),
            NewsFa("title9", "link", "description1 description1 description1 description1 description1 description1 description1", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.WINTER, guid = "https://homepages.cae.wisc.edu/~ece533/images/cat.png"),
            NewsFa("title10", "link", "description1 description1 description1 description1 description1 description1 description1", "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",seasons = Season.WINTER, guid = "https://homepages.cae.wisc.edu/~ece533/images/fruits.png")


        )
    }

    private fun createMockMetaData(): MutableList<MetaData> {
        return mutableListOf(
            MetaData(Season.SPRING,ItemType.MEDIUM),
            MetaData(Season.SPRING,ItemType.LARGE),
            MetaData(Season.SPRING,ItemType.MEDIUM),
            MetaData(Season.SPRING,ItemType.LARGE),
            MetaData(Season.SPRING,ItemType.MEDIUM),
            MetaData(Season.SPRING,ItemType.MEDIUM),
            MetaData(Season.SPRING,ItemType.MEDIUM),
            MetaData(Season.SPRING,ItemType.MEDIUM),
            MetaData(Season.SPRING,ItemType.LARGE),
            MetaData(Season.SPRING,ItemType.LARGE),


            MetaData(Season.SUMMER,ItemType.LARGE),
            MetaData(Season.SUMMER,ItemType.MEDIUM),
            MetaData(Season.SUMMER,ItemType.MEDIUM),


            MetaData(Season.FALL,ItemType.MEDIUM),
            MetaData(Season.FALL,ItemType.MEDIUM),
            MetaData(Season.FALL,ItemType.LARGE),
            MetaData(Season.FALL,ItemType.MEDIUM),
            MetaData(Season.FALL,ItemType.MEDIUM),
            MetaData(Season.FALL,ItemType.LARGE),
            MetaData(Season.FALL,ItemType.MEDIUM),
            MetaData(Season.FALL,ItemType.LARGE),
            MetaData(Season.FALL,ItemType.LARGE),
            MetaData(Season.FALL,ItemType.LARGE),


            MetaData(Season.WINTER,ItemType.LARGE),
            MetaData(Season.WINTER,ItemType.LARGE),
            MetaData(Season.WINTER,ItemType.LARGE),
            MetaData(Season.WINTER,ItemType.MEDIUM),
            MetaData(Season.WINTER,ItemType.MEDIUM),
            MetaData(Season.WINTER,ItemType.LARGE),
            MetaData(Season.WINTER,ItemType.MEDIUM),
            MetaData(Season.WINTER,ItemType.LARGE),
            MetaData(Season.WINTER,ItemType.MEDIUM),
            MetaData(Season.WINTER,ItemType.MEDIUM)
        )
    }

}


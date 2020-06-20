package ir.example.newstest.ui.home.json

import androidx.lifecycle.MutableLiveData
import ir.example.newstest.base.BaseViewModel
import ir.example.newstest.domain.pojo.Article
import javax.inject.Inject

class JsonFeedViewModel @Inject constructor(
) : BaseViewModel() {
    val newsList = MutableLiveData<List<Article>>()


}
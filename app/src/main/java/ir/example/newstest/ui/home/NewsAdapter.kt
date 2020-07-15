package ir.example.newstest.ui.home

import androidx.recyclerview.widget.DiffUtil
import ir.example.newstest.R
import ir.example.newstest.base.BaseAdapter
import ir.example.newstest.domain.pojo.NewsEn
import ir.example.newstest.domain.pojo.News
import ir.example.newstest.domain.pojo.NewsFa

class NewsAdapter : BaseAdapter<News>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                if (oldItem is NewsEn && newItem is NewsEn)
                    return oldItem == newItem
                if (oldItem is NewsFa && newItem is NewsFa)
                    return oldItem == newItem
                return false
            }

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                if (oldItem is NewsEn && newItem is NewsEn)
                    return (oldItem as NewsEn) == (newItem as NewsEn)
                if (oldItem is NewsFa && newItem is NewsFa)
                    return (oldItem as NewsFa) == (newItem as NewsFa)
                return false
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is NewsEn -> R.layout.item_news_en
            is NewsFa -> R.layout.item_news_fa
            else -> throw Exception()
        }
    }
}
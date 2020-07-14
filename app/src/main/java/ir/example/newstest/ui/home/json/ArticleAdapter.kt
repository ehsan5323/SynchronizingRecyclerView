package ir.example.newstest.ui.home.json

import androidx.recyclerview.widget.DiffUtil
import ir.example.newstest.R
import ir.example.newstest.base.BaseAdapter
import ir.example.newstest.domain.pojo.Article
import ir.example.newstest.domain.pojo.Detail
import ir.example.newstest.domain.pojo.News
import kotlinx.android.synthetic.main.item_news_en.view.*

class ArticleAdapter : BaseAdapter<News>(DIFF_CALLBACK) {


    companion object {
        @Suppress("USELESS_CAST")
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                if (oldItem is Article && newItem is Article)
                    return oldItem == newItem
                if (oldItem is Detail && newItem is Detail)
                    return oldItem == newItem
                return false
            }

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                if (oldItem is Article && newItem is Article)
                    return (oldItem as Article) == (newItem as Article)
                if (oldItem is Detail && newItem is Detail)
                    return (oldItem as Detail) == (newItem as Detail)
                return false
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Article -> R.layout.item_news_en
            is Detail -> R.layout.item_news_fa
            else -> throw Exception()
        }
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)


    }

}
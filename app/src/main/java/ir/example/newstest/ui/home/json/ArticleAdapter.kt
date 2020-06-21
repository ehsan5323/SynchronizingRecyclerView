package ir.example.newstest.ui.home.json

import androidx.recyclerview.widget.DiffUtil
import ir.example.newstest.R
import ir.example.newstest.base.BaseAdapter
import ir.example.newstest.domain.pojo.Article
import kotlinx.android.synthetic.main.item_news_en.view.*

class ArticleAdapter : BaseAdapter<Article>(DIFF_CALLBACK) {

    var onFavoriteClicked: ((article: Article) -> Unit)? = null

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_news_en
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.img_favorite_en?.setOnClickListener {
            val item = getItem(holder.adapterPosition)
            onFavoriteClicked?.invoke(item)
        }
    }

}
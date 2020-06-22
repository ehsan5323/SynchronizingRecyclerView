package ir.example.newstest.ui.home.xml

import androidx.recyclerview.widget.DiffUtil
import ir.example.newstest.R
import ir.example.newstest.base.BaseAdapter
import ir.example.newstest.domain.pojo.Detail
import kotlinx.android.synthetic.main.item_news_fa.view.*

class NewsFaAdapter : BaseAdapter<Detail>(DIFF_CALLBACK) {

    var onFavoriteClicked: ((detail: Detail) -> Unit)? = null

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Detail>() {
            override fun areItemsTheSame(oldItem: Detail, newItem: Detail): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Detail, newItem: Detail): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_news_fa
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.img_favorite_fa?.setOnClickListener {
            val item = getItem(holder.adapterPosition)
            onFavoriteClicked?.invoke(item)
        }
    }

}
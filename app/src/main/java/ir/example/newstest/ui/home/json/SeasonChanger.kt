package ir.example.newstest.ui.home.json

import androidx.recyclerview.widget.DiffUtil
import ir.example.newstest.R
import ir.example.newstest.base.BaseAdapter
import kotlinx.android.synthetic.main.item_news_fa.view.*

class SeasonChanger : BaseAdapter<Int>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Int>() {
            override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
                return oldItem == newItem
            }
        }

        var widthItem = 1

    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_season_changer
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.img_news.setImageResource(getItem(holder.adapterPosition))

        if (holder.itemView.width != 0)
            widthItem = holder.itemView.width // this will give you cell width dynamically


        holder.itemView.post {
            if (holder.itemView.width != 0)
                widthItem = holder.itemView.width // this will give you cell width dynamically

        }

    }

}
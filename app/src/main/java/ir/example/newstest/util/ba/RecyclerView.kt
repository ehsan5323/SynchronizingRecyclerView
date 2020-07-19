package ir.example.newstest.util.ba

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.example.newstest.base.BaseAdapter

@Suppress("UNCHECKED_CAST")
@BindingAdapter("data")
fun <T> RecyclerView.setRecyclerViewData(data: List<T>?) {
    if (adapter is BaseAdapter<*>) {
        (adapter as BaseAdapter<T>).submitList(data?.toMutableList())
    }
}

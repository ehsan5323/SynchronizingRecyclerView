package ir.example.newstest.util.ba

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.example.newstest.base.BaseAdapter
import ir.example.newstest.domain.base.Result

@Suppress("UNCHECKED_CAST")
@BindingAdapter("data")
fun <T> RecyclerView.setRecyclerViewData(data: Result<List<T>>?) {
    if (adapter is BaseAdapter<*> && data is Result.Success) {
        (adapter as BaseAdapter<T>).submitList(data.data.toMutableList())
    }
}

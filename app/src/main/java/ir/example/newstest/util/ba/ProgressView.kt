package ir.example.newstest.util.ba

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import ir.example.newstest.domain.util.Result

@Suppress("UNCHECKED_CAST")
@BindingAdapter("loading")
fun <T> ProgressBar.setLoadingBA(isLoading: Result<T>?) {
    visibility = if (isLoading is Result.Loading) View.VISIBLE
    else View.GONE
}

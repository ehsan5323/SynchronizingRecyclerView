package ir.example.newstest.util.ba

import android.view.View
import androidx.databinding.BindingAdapter
import ir.example.newstest.util.ktx.hide
import ir.example.newstest.util.ktx.show

@BindingAdapter("visibility", "isGone", requireAll = false)
fun View.visibility(isVisible: Boolean?, isGone: Boolean?) {
    if (isVisible == true) show()
    else hide(isGone ?: true)

}
package ir.example.newstest.util.ba

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import ir.example.newstest.util.ktx.load
import ir.example.newstest.util.ktx.loadRounded
import ir.example.newstest.util.ktx.px

@BindingAdapter("image", "placeholder", requireAll = false)
fun ImageView.imageUrl(url: String?, placeholder: Drawable?) {
    load(url, placeholder)
}


@BindingAdapter("imageRounded", "radius", "centerCrop", requireAll = false)
fun ImageView.setImageRounded(
    url: String?,
    radius: Int?,
    centerCrop: Boolean?
) {
    loadRounded(url, radius?.px ?: 0, centerCrop = centerCrop ?: false)
}
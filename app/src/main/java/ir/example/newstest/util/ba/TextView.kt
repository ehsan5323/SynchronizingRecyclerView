package ir.example.newstest.util.ba

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter
import ir.example.newstest.R
import ir.example.newstest.domain.pojo.Season

@BindingAdapter("season")
fun TextView.setSeasons(season: Season?) {
    when (season) {
        Season.SPRING -> {
            text = context.getString(R.string.spring)
            setTextColor(Color.BLACK)
            setBackgroundColor(Color.GREEN)
        }
        Season.SUMMER -> {
            text = context.getString(R.string.summer)
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.RED)
        }
        Season.FALL -> {
            text = context.getString(R.string.fall)
            setTextColor(Color.BLACK)
            setBackgroundColor(Color.YELLOW)
        }
        Season.WINTER -> {
            text = context.getString(R.string.winter)
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.BLUE)
        }
        Season.SPRING_N -> {
            text = context.getString(R.string.spring_new_year)
            setTextColor(Color.BLACK)
            setBackgroundColor(Color.GREEN)
        }
    }
}
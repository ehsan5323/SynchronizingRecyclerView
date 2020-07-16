package ir.example.newstest.util.ba

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter
import ir.example.newstest.domain.pojo.Season

@BindingAdapter("season")
fun TextView.setSeasons(season: Season?) {
    when (season) {
        Season.SPRING -> {
            text = "Spring"
            setTextColor(Color.BLACK)
            setBackgroundColor(Color.GREEN)
        }
        Season.SUMMER -> {
            text = "Summer"
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.RED)
        }
        Season.FALL -> {
            text = "Fall"
            setTextColor(Color.BLACK)
            setBackgroundColor(Color.YELLOW)
        }
        Season.WINTER -> {
            text = "Winter"
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.BLUE)
        }
        Season.SPRING_N -> {
            text = "NewYear"
            setTextColor(Color.BLACK)
            setBackgroundColor(Color.GREEN)
        }
    }
}
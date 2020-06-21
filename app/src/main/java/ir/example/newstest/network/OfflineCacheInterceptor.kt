package ir.example.newstest.network

import androidx.core.text.isDigitsOnly
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

const val CACHE_MAX_STALE = "CACHE_MAX_STALE"

private const val CACHE_CONTROL = "Cache-Control"

class OfflineCacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (!ConnectionLiveData.isConnected()) {

            val maxStale = request.header(CACHE_MAX_STALE)
            if (!maxStale.isNullOrBlank() && maxStale.isDigitsOnly()) {
                request = request.newBuilder().header(
                    CACHE_CONTROL,
                    CacheControl.Builder().maxAge(
                        maxStale.toInt(),
                        TimeUnit.SECONDS
                    ).build().toString()
                ).build()
            }
        }

        return chain.proceed(request)
    }
}
package ir.example.newstest.network

import androidx.core.text.isDigitsOnly
import ir.example.newstest.util.ConnectionLiveData
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

const val CACHE_MAX_STALE = "CACHE_MAX_STALE"
const val CACHE_CONTROL = "Cache-Control"
const val HEADER_PRAGMA = "Pragma"

class OfflineCacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (!ConnectionLiveData.isConnected()) {
            val maxStale = request.header(CACHE_MAX_STALE)
            if (!maxStale.isNullOrBlank() && maxStale.isDigitsOnly()) {
                request = request.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(CACHE_CONTROL)
                    .cacheControl(
                        CacheControl.Builder()
                            .maxStale(maxStale.toInt(), TimeUnit.SECONDS)
                            .build()
                    )
                    .build()
            }
        }
        return chain.proceed(request)
    }
}

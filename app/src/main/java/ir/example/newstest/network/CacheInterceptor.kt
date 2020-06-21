package ir.example.newstest.network

import androidx.core.text.isDigitsOnly
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

const val CACHE_MAX_AGE = "CACHE_MAX_AGE"

private const val CACHE_CONTROL = "Cache-Control"

class CacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val responseBuilder = chain.proceed(request).newBuilder()

        val maxAge = request.header(CACHE_MAX_AGE)
        if (!maxAge.isNullOrBlank() && maxAge.isDigitsOnly()) {
            responseBuilder.header(
                CACHE_CONTROL,
                CacheControl.Builder().maxAge(maxAge.toInt(), TimeUnit.SECONDS).build().toString()
            )
        }

        return responseBuilder.build()
    }
}
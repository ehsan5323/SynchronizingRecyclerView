package ir.example.newstest.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import ir.example.newstest.BuildConfig
import ir.example.newstest.application.NewsTestApp
import ir.example.newstest.data.di.qualifire.RetrofitJsonQualifier
import ir.example.newstest.data.di.qualifire.RetrofitXmlQualifier
import ir.example.newstest.data.restful.NewsJsonApi
import ir.example.newstest.data.restful.NewsXmlApi
import ir.example.newstest.util.SecretFields
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val CACHE_NAME = "network_cache"
private const val CACHE_SIZE = 10 * 1024 * 1024L // 10MB

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }
        builder.apply {
            connectTimeout(20L, TimeUnit.SECONDS)
            readTimeout(20L, TimeUnit.SECONDS)
            writeTimeout(20L, TimeUnit.SECONDS)
        }
        return builder.build()
    }

    @RetrofitXmlQualifier
    @Singleton
    @Provides
    fun provideRetrofitXml(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().client(okHttpClient)
            .baseUrl(SecretFields().xmlBaseURI())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
    }

    @RetrofitJsonQualifier
    @Singleton
    @Provides
    fun provideRetrofitGson(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().client(okHttpClient)
            .baseUrl(SecretFields().jsonBaseURI())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideXmlApi(@RetrofitXmlQualifier retrofit: Retrofit): NewsXmlApi {
        return retrofit.create(NewsXmlApi::class.java)
    }

    @Singleton
    @Provides
    fun provideJsonApi(@RetrofitJsonQualifier retrofit: Retrofit): NewsJsonApi {
        return retrofit.create(NewsJsonApi::class.java)
    }
}

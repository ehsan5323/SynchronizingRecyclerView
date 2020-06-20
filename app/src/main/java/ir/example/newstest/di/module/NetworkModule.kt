package ir.example.newstest.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import ir.example.newstest.BuildConfig
import ir.example.newstest.data.qualifire.RetrofitJsonQualifier
import ir.example.newstest.data.qualifire.RetrofitXmlQualifier
import ir.example.newstest.data.restful.NewsJsonApi
import ir.example.newstest.data.restful.NewsXmlApi
import ir.example.newstest.util.SecretFields
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
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
        builder.connectTimeout(20L, TimeUnit.SECONDS)
        builder.readTimeout(20L, TimeUnit.SECONDS)
        builder.writeTimeout(20L, TimeUnit.SECONDS)
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
    fun provideXmlService(@RetrofitXmlQualifier retrofit: Retrofit): NewsXmlApi {
        return retrofit.create(NewsXmlApi::class.java)
    }

    @Singleton
    @Provides
    fun provideJsonService(@RetrofitJsonQualifier retrofit: Retrofit): NewsJsonApi {
        return retrofit.create(NewsJsonApi::class.java)
    }
}
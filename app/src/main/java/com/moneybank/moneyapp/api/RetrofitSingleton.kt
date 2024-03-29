package com.moneybank.moneyapp.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.moneybank.moneyapp.application.MiniMoneyBoxApplication
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * [RetrofitSingleton] :
 *
 * @author : Nirav Joshi
 * @version 1.0.0
 * @since 10/15/2019
 */
class RetrofitSingleton private constructor() {
    object HOLDER {
        val instance = RetrofitSingleton()
    }

    companion object {
        private const val TAG = "RetrofitSingleton"
        @JvmStatic
        fun getInstance() = HOLDER.instance
    }

    private val httpCacheDirectory = File(MiniMoneyBoxApplication.context.cacheDir, "responses")
    private val cache = Cache(httpCacheDirectory, (10 * 1024 * 1024).toLong())

    fun provideApiService(): IAppWebApi {
        return provideRetrofit().create(IAppWebApi::class.java)
    }

    private fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(provideGson())
    }

    private fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    private fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    private fun getOkHttpClient(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        builder.cache(cache)
        builder.hostnameVerifier { hostname, session -> hostname.equals(session.peerHost, ignoreCase = true) }
        builder.connectTimeout(80, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val original = chain.request()
                chain.proceed(with(original.newBuilder()) {
                    val token = MiniMoneyBoxApplication.context.getAccessToken()
                    if (token.isNullOrEmpty().not())
                        header("Authorization","Bearer ".plus(token))
                    header("Content-Type", "")
                    header("Accept", "application/json")
                    header("AppId", "3a97b932a9d449c981b595")
                    header("appVersion", "5.10.0")
                    header("apiVersion", "3.0.0")
                    method(original.method(), original.body())
                    build()
                })
            }



        builder.addNetworkInterceptor(networkCacheInterceptor())
        builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return builder
    }

    private fun networkCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(2, TimeUnit.MINUTES)
                .build()
            response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
            response
        }
    }

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api-test01.moneyboxapp.com/")
            .addConverterFactory(provideGsonConverterFactory())
            .addCallAdapterFactory(provideRxJavaCallAdapterFactory())
            .client(getOkHttpClient().build())
            .build()
    }
}
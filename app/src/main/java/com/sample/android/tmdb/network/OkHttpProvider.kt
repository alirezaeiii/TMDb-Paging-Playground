package com.sample.android.tmdb.network

import com.sample.android.tmdb.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.IOException

object OkHttpProvider {

    val instance: OkHttpClient

    init {
        val logger = HttpLoggingInterceptor {
            Timber.d(it)
        }
        logger.level = HttpLoggingInterceptor.Level.BASIC

        instance = OkHttpClient.Builder()
                .addInterceptor(RequestInterceptor())
                .addInterceptor(logger)
                .build()
    }

    private class RequestInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                    .build()

            val request = original.newBuilder().url(url).build()
            return chain.proceed(request)
        }
    }
}
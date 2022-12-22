package com.sample.android.tmdb.di

import com.sample.android.tmdb.BuildConfig
import com.sample.android.tmdb.data.network.MovieApi
import com.sample.android.tmdb.data.network.OkHttpProvider
import com.sample.android.tmdb.data.network.PersonApi
import com.sample.android.tmdb.data.network.TVShowApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun retrofit(): Retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.TMDB_BASE_URL)
                    .client(OkHttpProvider.instance)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

    @Singleton
    @Provides
    fun movieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    @Singleton
    @Provides
    fun tvShowApi(retrofit: Retrofit): TVShowApi = retrofit.create(TVShowApi::class.java)

    @Singleton
    @Provides
    fun personApi(retrofit: Retrofit): PersonApi = retrofit.create(PersonApi::class.java)
}
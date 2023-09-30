package com.sample.android.tmdb.di

import com.sample.android.tmdb.BuildConfig
import com.sample.android.tmdb.data.network.MovieService
import com.sample.android.tmdb.data.network.OkHttpProvider
import com.sample.android.tmdb.data.network.PersonService
import com.sample.android.tmdb.data.network.TVShowService
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
    fun movieApi(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)

    @Singleton
    @Provides
    fun tvShowApi(retrofit: Retrofit): TVShowService = retrofit.create(TVShowService::class.java)

    @Singleton
    @Provides
    fun personApi(retrofit: Retrofit): PersonService = retrofit.create(PersonService::class.java)
}
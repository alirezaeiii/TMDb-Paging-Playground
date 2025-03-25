package com.sample.android.tmdb.di

import android.app.Application
import com.sample.android.tmdb.util.NetworkUtils
import dagger.Module
import dagger.Provides

@Module
class AppUtilsModule {

    @Provides
    fun provideNetworkUtils(application: Application): NetworkUtils {
        return NetworkUtils(application)
    }
}
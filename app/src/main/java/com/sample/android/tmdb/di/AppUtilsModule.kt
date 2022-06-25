package com.sample.android.tmdb.di

import android.content.Context
import com.sample.android.tmdb.util.NetworkUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppUtilsModule {
    @Singleton
    @Provides
    fun provideNetworkUtils(context: Context): NetworkUtils {
        return NetworkUtils(context)
    }
}
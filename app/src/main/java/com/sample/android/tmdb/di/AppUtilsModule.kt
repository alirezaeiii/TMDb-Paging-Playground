package com.sample.android.tmdb.di

import android.content.Context
import com.sample.android.tmdb.util.NetworkUtils
import dagger.Module
import dagger.Provides

@Module
class AppUtilsModule {

    @Provides
    fun provideNetworkUtils(context: Context): NetworkUtils {
        return NetworkUtils(context)
    }
}
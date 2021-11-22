package com.sample.android.tmdb.di

import com.sample.android.tmdb.util.Analytics
import com.sample.android.tmdb.util.Firebase
import dagger.Binds
import dagger.Module

@Module
abstract class AnalyticsModule {

    @Binds
    abstract fun bindFirebase(firebase: Firebase): Analytics
}
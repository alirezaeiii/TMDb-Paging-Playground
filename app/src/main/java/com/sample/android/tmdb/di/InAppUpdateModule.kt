package com.sample.android.tmdb.di

import com.sample.android.tmdb.ui.start.InAppUpdateHandler
import com.sample.android.tmdb.ui.start.StartActivity
import dagger.Module
import dagger.Provides

@Module
class InAppUpdateModule {

    @Provides
    internal fun provideInAppUpdate(activity: StartActivity): InAppUpdateHandler =
        InAppUpdateHandler(activity)
}
package com.sample.android.tmdb

import com.sample.android.tmdb.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class MovieApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        // Set up Timber
        Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}
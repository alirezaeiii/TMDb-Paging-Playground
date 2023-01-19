package com.sample.android.tmdb

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.sample.android.tmdb.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class Application : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        // Set up Timber
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        initFirebaseRemoteConfig()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    private fun initFirebaseRemoteConfig() {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) 0 else 3600
        }
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val updated = task.result
                Timber.d("Remote Config params updated: $updated")
            } else {
                Timber.w("Remote Config fetch unsuccessful")
            }
        }
    }
}
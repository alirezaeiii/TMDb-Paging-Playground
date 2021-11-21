package com.sample.android.tmdb.util

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import javax.inject.Singleton

interface Analytics {
    fun logEventScreenView(name: String, sortTpe: String)
}

@Singleton
class Firebase @Inject constructor() : Analytics {

    override fun logEventScreenView(name: String, sortTpe: String) {
        Firebase.analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, name)
            param(FirebaseAnalytics.Param.CONTENT_TYPE, sortTpe)
        }
    }

    companion object {
        const val ANALYTICS_SEARCH = "search"
        const val ANALYTICS_DETAIL = "detail"
        const val ANALYTICS_PERSON = "person"
    }
}
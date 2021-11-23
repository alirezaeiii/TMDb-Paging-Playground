package com.sample.android.tmdb.util

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import javax.inject.Singleton

interface Analytics {
    fun logEventScreenView(screenName: String)
}

@Singleton
class Firebase @Inject constructor() : Analytics {

    override fun logEventScreenView(screenName: String) {
        Firebase.analytics.logEvent(screenName.lowercase(), null)
    }

    companion object {
        const val ANALYTICS_SEARCH_ACTION = "search"
        const val ANALYTICS_DETAIL_SCREEN = "detail"
        const val ANALYTICS_PERSON_SCREEN = "person"
    }
}
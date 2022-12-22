package com.sample.android.tmdb

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.sample.android.tmdb.data.network.OkHttpProvider
import org.junit.After
import org.junit.Before

open class BaseIdlingResource {

    private val resource: IdlingResource =
        OkHttp3IdlingResource.create("OkHttp", OkHttpProvider.instance)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(resource)
    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */
    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(resource)
    }
}
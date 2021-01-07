package com.sample.android.tmdb

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.ui.TmdbItemViewHolder
import com.sample.android.tmdb.util.EspressoIdlingResource
import com.sample.android.tmdb.utils.TestRecyclerViewUtils.customScrollTo
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TestTrailer {

    @Rule
    @JvmField
    val intentTestRule = IntentsTestRule(MainActivity::class.java)

    /**
     * Prepare your test fixture for this test. In this case we register an IdlingResources with
     * Espresso. IdlingResource resource is a great way to tell Espresso when your app is in an
     * idle state. This helps Espresso to synchronize your test actions, which makes tests
     * significantly more reliable.
     */
    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource())
    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */
    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource())
    }

    @Test
    fun shouldBeAbleToDisplayTrailer() {
        onView(ViewMatchers.withId(R.id.recyclerView)).perform(RecyclerViewActions
                .actionOnItemAtPosition<TmdbItemViewHolder>(0, click()))

        onView(ViewMatchers.withId(R.id.trailer_scroll_view)).perform(customScrollTo, click())

        intended(allOf(hasAction(Intent.ACTION_VIEW)))
    }
}
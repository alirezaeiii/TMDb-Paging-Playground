package com.sample.android.tmdb

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import com.sample.android.tmdb.base.TestEspressoBase
import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.ui.TmdbViewHolder
import com.sample.android.tmdb.utils.TestRecyclerViewUtils.customScrollTo
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TestTrailer : TestEspressoBase() {

    @Rule
    @JvmField
    val intentTestRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun shouldBeAbleToDisplayTrailer() {
        onView(ViewMatchers.withId(R.id.list)).perform(RecyclerViewActions
                .actionOnItemAtPosition<TmdbViewHolder>(0, click()))

        onView(ViewMatchers.withId(R.id.trailer_scroll_view)).perform(customScrollTo, click())

        intended(allOf(hasAction(Intent.ACTION_VIEW)))
    }
}
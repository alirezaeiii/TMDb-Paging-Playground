package com.sample.android.tmdb

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasAction
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import com.sample.android.tmdb.TestUtils.customScrollTo
import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.ui.MovieViewHolder
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TestTrailer : TestBase() {

    @Rule
    @JvmField
    val intentTestRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun shouldBeAbleToDisplayTrailer() {
        onView(ViewMatchers.withId(R.id.list)).perform(RecyclerViewActions
                .actionOnItemAtPosition<MovieViewHolder>(7, ViewActions.click()))

        onView(ViewMatchers.withId(R.id.trailer_scroll_view)).perform(customScrollTo, click())

        intended(allOf(hasAction(Intent.ACTION_VIEW)))
    }

}
package com.sample.android.tmdb

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.sample.android.tmdb.TestUtils.customScrollTo
import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.ui.item.movie.MovieViewHolder
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
                .actionOnItemAtPosition<MovieViewHolder>(7, click()))

        onView(ViewMatchers.withId(R.id.trailer_scroll_view)).perform(customScrollTo, click())

        intended(allOf(hasAction(Intent.ACTION_VIEW)))
    }

}
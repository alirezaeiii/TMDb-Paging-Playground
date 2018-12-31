package com.sample.android.tmdb

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.widget.EditText
import com.sample.android.tmdb.TestUtils.*
import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.ui.MovieViewHolder
import com.sample.android.tmdb.ui.detail.CastAdapter
import com.sample.android.tmdb.util.EspressoIdlingResource
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TestMainActivity {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

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
    fun shouldBeAbleToLaunchMainScreen() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.action_search)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToLoadMovies() {
        onView(withId(R.id.list)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToScrollViewMovieDetails() {
        onView(withId(R.id.list)).perform(RecyclerViewActions
                .actionOnItemAtPosition<MovieViewHolder>(10, click()))
        onView(withText(R.string.summary)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToDisplayTrailerLabel() {
        onView(withId(R.id.list)).perform(RecyclerViewActions
                .actionOnItemAtPosition<MovieViewHolder>(10, click()))
        onView(withText(R.string.trailers)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToDisplayCastLabel() {
        onView(withId(R.id.list)).perform(RecyclerViewActions
                .actionOnItemAtPosition<MovieViewHolder>(10, click()))
        onView(withText(R.string.cast)).perform(nestedScrollTo()).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToDisplayCast() {
        onView(withId(R.id.list)).perform(RecyclerViewActions
                .actionOnItemAtPosition<MovieViewHolder>(10, click()))
        onView(withId(R.id.cast_list)).perform(nestedScrollTo()).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToDisplayPersonDetail() {
        onView(withId(R.id.list)).perform(RecyclerViewActions
                .actionOnItemAtPosition<MovieViewHolder>(10, click()))
        onView(withId(R.id.cast_list)).perform(nestedScrollTo()).perform(RecyclerViewActions
                .actionOnItemAtPosition<CastAdapter.CastViewHolder>(2, click()))
        onView(withText(R.string.biography)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToChangeTabAndViewDetails() {
        onView(withId(R.id.action_upcoming)).perform(click())
        onView(withId(R.id.list)).perform(RecyclerViewActions
                .actionOnItemAtPosition<MovieViewHolder>(10, click()))
        onView(withText(R.string.summary)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToSearchItem() {
        onView(withId(R.id.action_search)).perform(click())

        onView(isAssignableFrom(EditText::class.java))
                .perform(typeText("Harry Potter"),
                        pressImeActionButton())

        // Check error message view is not displayed
        onView(withId(R.id.error_msg)).check(matches(not<View>(isDisplayed())))

        onView(withId(R.id.list)).check(matches(isDisplayed()))
    }
}

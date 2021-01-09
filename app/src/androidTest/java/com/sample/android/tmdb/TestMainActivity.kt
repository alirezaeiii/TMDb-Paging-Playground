package com.sample.android.tmdb

import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.sample.android.tmdb.network.OkHttpProvider
import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.ui.TmdbItemViewHolder
import com.sample.android.tmdb.ui.detail.credit.CreditAdapter
import com.sample.android.tmdb.utils.TestRecyclerViewUtils.nestedScrollTo
import com.sample.android.tmdb.utils.matchCurrentTabTitle
import com.sample.android.tmdb.utils.matchTabTitleAtPosition
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TestMainActivity {

    private val resource : IdlingResource = OkHttp3IdlingResource.create("OkHttp", OkHttpProvider.instance)

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

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

    @Test
    fun shouldBeAbleToLaunchMainScreen() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.action_search)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToLoadMovies() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToScrollViewMovieDetails() {
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions
                .actionOnItemAtPosition<TmdbItemViewHolder>(10, click()))
        onView(withText(R.string.summary)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToDisplayTrailerLabel() {
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions
                .actionOnItemAtPosition<TmdbItemViewHolder>(0, click()))
        onView(withText(R.string.trailers)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToDisplayCastTab() {
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions
                .actionOnItemAtPosition<TmdbItemViewHolder>(10, click()))
        onView(withId(R.id.tab_layout)).check(matches(matchCurrentTabTitle("Cast")))

    }

    @Test
    fun shouldBeAbleToDisplayCrewTab() {
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions
                .actionOnItemAtPosition<TmdbItemViewHolder>(0, click()))
        onView(withId(R.id.tab_layout)).check(matches(matchTabTitleAtPosition("Crew", 1)))
    }

    @Test
    fun shouldBeAbleToDisplayPersonDetail() {
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions
                .actionOnItemAtPosition<TmdbItemViewHolder>(0, click()))
        onView(withId(R.id.pager)).perform(nestedScrollTo()).check(matches(isDisplayed()))
        onView(withId(R.id.credit_list)).perform(RecyclerViewActions
                .actionOnItemAtPosition<CreditAdapter.CreditViewHolder>(2, click()))
        onView(withText(R.string.biography)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToChangeTabAndViewDetails() {
        onView(withId(R.id.action_upcoming)).perform(click())
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions
                .actionOnItemAtPosition<TmdbItemViewHolder>(10, click()))
        onView(withText(R.string.summary)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToSearchItem() {
        onView(withId(R.id.action_search)).perform(click())
        onView(isAssignableFrom(EditText::class.java))
                .perform(typeText("Harry Potter"),
                        pressImeActionButton())
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }
}

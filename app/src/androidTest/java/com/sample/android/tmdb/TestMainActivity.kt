package com.sample.android.tmdb

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ScrollToAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.sample.android.tmdb.network.OkHttpProvider
import com.sample.android.tmdb.ui.detail.credit.CreditAdapter
import com.sample.android.tmdb.ui.feed.NavType
import com.sample.android.tmdb.ui.paging.TmdbItemViewHolder
import com.sample.android.tmdb.ui.paging.main.MainActivity
import com.sample.android.tmdb.ui.paging.main.SortType
import com.sample.android.tmdb.util.Constants
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.ViewMatchers.withParent
import android.widget.TextView
import androidx.test.espresso.*
import org.hamcrest.CoreMatchers.*


@RunWith(AndroidJUnit4::class)
@LargeTest
class TestMainActivity {

    private val resource: IdlingResource =
        OkHttp3IdlingResource.create("OkHttp", OkHttpProvider.instance)

    @Rule
    @JvmField
    val activityTestRule: ActivityTestRule<MainActivity> = object : ActivityTestRule<MainActivity>(
        MainActivity::class.java
    ) {
        override fun getActivityIntent(): Intent {
            val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
            val intent = Intent(targetContext, MainActivity::class.java).apply {
                putExtras(Bundle().apply {
                    putParcelable(Constants.EXTRA_SORT_TYPE, SortType.MOST_POPULAR)
                    putParcelable(Constants.EXTRA_NAV_TYPE, NavType.MOVIES)
                })
            }
            return intent
        }
    }

    private val scrollNestedScrollView: ViewAction = object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return AllOf.allOf(
                withEffectiveVisibility(Visibility.VISIBLE), isDescendantOfA(
                    anyOf(isAssignableFrom(NestedScrollView::class.java))
                )
            )
        }

        override fun perform(uiController: UiController, view: View) {
            ScrollToAction().perform(uiController, view)
        }

        override fun getDescription(): String? {
            return null
        }
    }

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
    fun shouldBeAbleToDisplayToolbar() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToDisplayToolbarTitle() {
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.toolbar))))
            .check(matches(withText("Popular Movies")))
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
    fun shouldBeAbleToDisplayCastLabel() {
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions
                .actionOnItemAtPosition<TmdbItemViewHolder>(0, click()))
        onView(withId(R.id.cast_list)).perform(scrollNestedScrollView)
        onView(withText(R.string.cast)).check(matches(isDisplayed()))

    }

    @Test
    fun shouldBeAbleToDisplayCrewLabel() {
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions
                .actionOnItemAtPosition<TmdbItemViewHolder>(0, click()))
        onView(withId(R.id.crew_list)).perform(scrollNestedScrollView)
        onView(withText(R.string.crew)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToDisplayCastDetail() {
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions
                .actionOnItemAtPosition<TmdbItemViewHolder>(0, click()))
        onView(withId(R.id.cast_list)).perform(scrollNestedScrollView)
        onView(withId(R.id.cast_list)).perform(RecyclerViewActions
                .actionOnItemAtPosition<CreditAdapter.CreditViewHolder>(0, click()))
        onView(withText(R.string.biography)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToDisplayCrewDetail() {
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions
            .actionOnItemAtPosition<TmdbItemViewHolder>(0, click()))
        onView(withId(R.id.crew_list)).perform(scrollNestedScrollView)
        onView(withId(R.id.crew_list)).perform(RecyclerViewActions
            .actionOnItemAtPosition<CreditAdapter.CreditViewHolder>(1, click()))
        onView(withText(R.string.biography)).check(matches(isDisplayed()))
    }
}

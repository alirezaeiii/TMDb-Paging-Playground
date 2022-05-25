package com.sample.android.tmdb

import android.view.View
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ScrollToAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.sample.android.tmdb.ui.detail.credit.CreditAdapter
import com.sample.android.tmdb.ui.paging.TmdbItemViewHolder
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf
import org.junit.Test

abstract class BaseMainActivity: BaseIdlingResource() {

    protected abstract val title: String

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

    @Test
    fun shouldBeAbleToDisplayToolbar() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToDisplayToolbarTitle() {
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.toolbar))))
            .check(matches(withText(title)))
    }

    @Test
    fun shouldBeAbleToLoadItems() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeAbleToDisplayDetails() {
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

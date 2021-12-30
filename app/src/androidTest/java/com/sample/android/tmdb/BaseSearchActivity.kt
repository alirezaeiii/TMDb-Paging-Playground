package com.sample.android.tmdb

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.sample.android.tmdb.ui.feed.NavType
import com.sample.android.tmdb.ui.paging.search.SearchActivity
import com.sample.android.tmdb.util.Constants.EXTRA_NAV_TYPE
import org.junit.Rule
import org.junit.Test

abstract class BaseSearchActivity: BaseIdlingResource() {

    protected abstract val navType: NavType

    @Rule
    @JvmField
    val activityTestRule: ActivityTestRule<SearchActivity> = object : ActivityTestRule<SearchActivity>(
        SearchActivity::class.java
    ) {
        override fun getActivityIntent(): Intent {
            val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
            val intent = Intent(targetContext, SearchActivity::class.java).apply {
                putExtras(Bundle().apply {
                    putParcelable(EXTRA_NAV_TYPE, navType)
                })
            }
            return intent
        }
    }

    @Test
    fun shouldBeAbleToSearch() {
        onView(isAssignableFrom(EditText::class.java))
            .perform(typeText("Harry Potter"),
                pressImeActionButton())
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }
}
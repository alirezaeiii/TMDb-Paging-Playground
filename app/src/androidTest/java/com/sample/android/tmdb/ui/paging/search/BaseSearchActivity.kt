package com.sample.android.tmdb.ui.paging.search

import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.sample.android.tmdb.BaseIdlingResource
import org.junit.Test
import com.sample.android.tmdb.R

abstract class BaseSearchActivity: BaseIdlingResource() {

    @Test
    fun shouldBeAbleToSearch() {
        onView(isAssignableFrom(EditText::class.java))
            .perform(typeText("Harry Potter"),
                pressImeActionButton())
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }
}
package com.sample.android.tmdb.ui.paging.search.movie

import android.content.Context
import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.sample.android.tmdb.ui.paging.search.BaseSearchActivity
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestSearchMovies : BaseSearchActivity() {

    @Rule
    @JvmField
    val activityTestRule: ActivityTestRule<SearchMovieActivity> =
        object : ActivityTestRule<SearchMovieActivity>(
            SearchMovieActivity::class.java
        ) {
            override fun getActivityIntent(): Intent {
                val targetContext: Context =
                    InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, SearchMovieActivity::class.java)
            }
        }
}
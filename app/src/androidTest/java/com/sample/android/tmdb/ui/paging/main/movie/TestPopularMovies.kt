package com.sample.android.tmdb.ui.paging.main.movie

import android.content.Context
import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.sample.android.tmdb.ui.paging.main.BaseMainActivity
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TestPopularMovies : BaseMainActivity() {

    @Rule
    @JvmField
    val activityTestRule: ActivityTestRule<PopularMoviesActivity> =
        object : ActivityTestRule<PopularMoviesActivity>(
            PopularMoviesActivity::class.java
        ) {
            override fun getActivityIntent(): Intent {
                val targetContext: Context =
                    InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, PopularMoviesActivity::class.java)
            }
        }

    override val title: String
        get() = "Popular Movies"
}
package com.sample.android.tmdb.main

import android.content.Context
import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.sample.android.tmdb.BaseMainActivity
import com.sample.android.tmdb.ui.paging.main.tvshow.PopularTVShowActivity
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TestPopularTVSeries : BaseMainActivity() {

    @Rule
    @JvmField
    val activityTestRule: ActivityTestRule<PopularTVShowActivity> =
        object : ActivityTestRule<PopularTVShowActivity>(
            PopularTVShowActivity::class.java
        ) {
            override fun getActivityIntent(): Intent {
                val targetContext: Context =
                    InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, PopularTVShowActivity::class.java)
            }
        }

    override val title: String
        get() = "Popular TV Series"
}
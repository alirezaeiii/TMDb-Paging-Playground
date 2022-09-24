package com.sample.android.tmdb.ui.paging.tvshow

import android.content.Context
import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.sample.android.tmdb.ui.BaseMainActivity
import com.sample.android.tmdb.ui.paging.main.tvshow.LatestTVShowActivity
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TestUpcomingTVSeries : BaseMainActivity() {

    @Rule
    @JvmField
    val activityTestRule: ActivityTestRule<LatestTVShowActivity> =
        object : ActivityTestRule<LatestTVShowActivity>(
            LatestTVShowActivity::class.java
        ) {
            override fun getActivityIntent(): Intent {
                val targetContext: Context =
                    InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, LatestTVShowActivity::class.java)
            }
        }

    override val title: String
        get() = "Upcoming TV Series"
}
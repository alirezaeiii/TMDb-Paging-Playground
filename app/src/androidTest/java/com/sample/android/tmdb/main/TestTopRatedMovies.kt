package com.sample.android.tmdb.main

import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import com.sample.android.tmdb.BaseMainActivity
import com.sample.android.tmdb.ui.feed.NavType
import com.sample.android.tmdb.ui.paging.main.SortType
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TestTopRatedMovies: BaseMainActivity() {

    override val sortType: SortType
        get() = SortType.HIGHEST_RATED

    override val navType: NavType
        get() = NavType.MOVIES

    override val title: String
        get() = "Top Rated Movies"
}
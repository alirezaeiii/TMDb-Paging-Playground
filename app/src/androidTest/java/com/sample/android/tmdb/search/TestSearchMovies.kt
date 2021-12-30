package com.sample.android.tmdb.search

import androidx.test.runner.AndroidJUnit4
import com.sample.android.tmdb.BaseSearchActivity
import com.sample.android.tmdb.ui.feed.NavType
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestSearchMovies: BaseSearchActivity() {

    override val navType: NavType
        get() = NavType.MOVIES
}
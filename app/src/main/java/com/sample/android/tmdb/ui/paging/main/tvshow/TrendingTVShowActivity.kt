package com.sample.android.tmdb.ui.paging.main.tvshow

import com.sample.android.tmdb.R
import javax.inject.Inject

class TrendingTVShowActivity: TVShowActivity() {

    @Inject
    lateinit var trendingTVShowFragment: TrendingTVShowFragment

    override val titleId: Int
        get() = R.string.trending

    override val fragment: TVShowFragment
        get() = trendingTVShowFragment
}
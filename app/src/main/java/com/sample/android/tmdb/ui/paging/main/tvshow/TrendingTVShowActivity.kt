package com.sample.android.tmdb.ui.paging.main.tvshow

import com.sample.android.tmdb.R
import javax.inject.Inject

class TrendingTVShowActivity: TVShowPagingActivity() {

    @Inject
    lateinit var trendingTVShowFragment: TrendingTVShowFragment

    override val titleId: Int
        get() = R.string.trending

    override val fragment: TVShowPagingFragment
        get() = trendingTVShowFragment
}
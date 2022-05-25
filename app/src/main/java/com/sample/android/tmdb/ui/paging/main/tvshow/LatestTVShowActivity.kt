package com.sample.android.tmdb.ui.paging.main.tvshow

import com.sample.android.tmdb.R
import javax.inject.Inject

class LatestTVShowActivity: TVShowActivity() {

    @Inject
    lateinit var latestTvShowFragment: LatestTVShowFragment

    override val titleId: Int
        get() = R.string.upcoming

    override val fragment: TVShowFragment
        get() = latestTvShowFragment
}
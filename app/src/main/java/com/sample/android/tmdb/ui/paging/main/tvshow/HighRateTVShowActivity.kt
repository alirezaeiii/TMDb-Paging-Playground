package com.sample.android.tmdb.ui.paging.main.tvshow

import com.sample.android.tmdb.R
import javax.inject.Inject

class HighRateTVShowActivity: TVShowPagingActivity() {

    @Inject
    lateinit var highRateTVShowFragment: HighRateTVShowFragment

    override val titleId: Int
        get() = R.string.highest_rate

    override val fragment: TVShowPagingFragment
        get() = highRateTVShowFragment
}
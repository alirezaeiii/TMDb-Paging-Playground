package com.sample.android.tmdb.ui.paging.main.tvshow

import com.sample.android.tmdb.R
import javax.inject.Inject

class OnTheAirTVShowActivity: TVShowPagingActivity() {

    @Inject
    lateinit var onTheAirTVShowFragment: OnTheAirTVShowFragment

    override val titleId: Int
        get() = R.string.on_the_air

    override val fragment: TVShowPagingFragment
        get() = onTheAirTVShowFragment
}
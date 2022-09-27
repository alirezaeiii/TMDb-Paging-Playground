package com.sample.android.tmdb.ui.paging.main.tvshow

import com.sample.android.tmdb.R
import javax.inject.Inject

class OnTheAirTVShowActivity: TVShowActivity() {

    @Inject
    lateinit var onTheAirTVShowFragment: OnTheAirTVShowFragment

    override val titleId: Int
        get() = R.string.on_the_air

    override val fragment: TVShowFragment
        get() = onTheAirTVShowFragment
}
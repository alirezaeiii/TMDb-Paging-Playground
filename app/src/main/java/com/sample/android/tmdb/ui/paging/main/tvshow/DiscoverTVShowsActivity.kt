package com.sample.android.tmdb.ui.paging.main.tvshow

import com.sample.android.tmdb.R
import javax.inject.Inject

class DiscoverTVShowsActivity : TVShowActivity() {

    @Inject
    lateinit var discoverTVShowFragment: DiscoverTVShowsFragment

    override val titleId: Int
        get() = R.string.discover

    override val fragment: TVShowFragment
        get() = discoverTVShowFragment
}
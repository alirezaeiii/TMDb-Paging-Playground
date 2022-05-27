package com.sample.android.tmdb.ui.paging.main.tvshow

import com.sample.android.tmdb.R
import javax.inject.Inject

class PopularTVShowActivity: TVShowActivity() {

    @Inject
    lateinit var popularTVShowFragment: PopularTVShowFragment

    override val titleId: Int
        get() = R.string.popular

    override val fragment: TVShowFragment
        get() = popularTVShowFragment
}
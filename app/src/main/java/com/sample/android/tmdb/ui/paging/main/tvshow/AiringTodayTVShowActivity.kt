package com.sample.android.tmdb.ui.paging.main.tvshow

import com.sample.android.tmdb.R
import javax.inject.Inject

class AiringTodayTVShowActivity: TVShowActivity() {

    @Inject
    lateinit var airingTodayTVShowFragment: AiringTodayTVShowsFragment

    override val titleId: Int
        get() = R.string.airing_today

    override val fragment: TVShowFragment
        get() = airingTodayTVShowFragment
}
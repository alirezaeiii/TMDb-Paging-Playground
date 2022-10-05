package com.sample.android.tmdb.ui.paging.search.tvshow

import com.sample.android.tmdb.R
import com.sample.android.tmdb.data.TVShow
import com.sample.android.tmdb.ui.paging.search.SearchActivity
import javax.inject.Inject

class SearchTVShowActivity: SearchActivity<TVShow>() {

    @Inject
    lateinit var searchTVShowFragment: SearchTVShowFragment

    override val fragment: SearchTVShowFragment
        get() = searchTVShowFragment

    override val hintId: Int
        get() = R.string.search_hint_tv_shows
}
package com.sample.android.tmdb.ui.tvshow

import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.util.SortType.HIGHEST_RATED
import javax.inject.Inject

@ActivityScoped
class HighRateTVShowFragment @Inject
constructor() : TVShowFragment() {

    override val sortType= HIGHEST_RATED
}
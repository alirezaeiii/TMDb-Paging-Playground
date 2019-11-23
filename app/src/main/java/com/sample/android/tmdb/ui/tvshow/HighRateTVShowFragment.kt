package com.sample.android.tmdb.ui.tvshow

import com.sample.android.tmdb.util.SortType
import com.sample.android.tmdb.util.SortType.HIGHEST_RATED
import com.sample.android.tmdb.di.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class HighRateTVShowFragment @Inject
constructor() : TVShowFragment() {

    override fun getSortType(): SortType = HIGHEST_RATED
}
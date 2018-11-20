package com.sample.android.tmdb.ui.base

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.SortType.UPCOMING
import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.ui.base.BaseFragment
import javax.inject.Inject

@ActivityScoped
class UpcomingMoviesFragment @Inject
constructor() : BaseFragment() {

    override fun getSortType(): SortType = UPCOMING
}
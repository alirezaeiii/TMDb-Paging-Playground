package com.sample.android.tmdb.ui.base

import android.support.v7.app.ActionBar
import com.sample.android.tmdb.R
import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.ui.MainFragment
import com.sample.android.tmdb.ui.MovieViewModel

abstract class BaseFragment : MainFragment() {

    protected abstract fun getSortType(): SortType

    override fun initViewModel() {
        model = getViewModel()
        model.showQuery("")
    }

    override fun getMoviesViewModel(): MovieViewModel =
            MovieViewModel(dataSource = dataSource,
                    sortType = getSortType())

    override fun setupToolbar(ab : ActionBar?) {
        ab?.setTitle(R.string.app_name)
        ab?.setDisplayShowTitleEnabled(true)
    }
}
package com.sample.android.tmdb.ui.base

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.ui.MainFragment
import com.sample.android.tmdb.ui.MovieViewModel

abstract class BaseFragment : MainFragment() {

    protected abstract fun getSortType(): SortType

    override fun initViewModel() {
        super.initViewModel()
        model.showQuery("")
    }

    override fun getMoviesViewModel(): MovieViewModel =
            MovieViewModel(dataSource = dataSource,
                    sortType = getSortType())
}
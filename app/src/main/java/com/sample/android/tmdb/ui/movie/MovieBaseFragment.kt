package com.sample.android.tmdb.ui.movie

abstract class MovieBaseFragment : MovieFragment() {

    override fun initViewModel() {
        super.initViewModel()
        model.showQuery("")
    }
}
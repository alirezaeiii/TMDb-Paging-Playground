package com.sample.android.tmdb.ui.movie

abstract class MovieFragment : MovieBaseFragment() {

    override fun initViewModel() {
        super.initViewModel()
        model.showQuery("")
    }
}
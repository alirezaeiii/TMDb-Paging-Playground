package com.sample.android.tmdb.ui.tvshow

abstract class TVShowBaseFragment : TVShowFragment() {

    override fun initViewModel() {
        super.initViewModel()
        model.showQuery("")
    }
}
package com.sample.android.tmdb.ui.base

import com.sample.android.tmdb.ui.MainFragment

abstract class BaseFragment : MainFragment() {

    override fun initViewModel() {
        super.initViewModel()
        model.showQuery("")
    }
}
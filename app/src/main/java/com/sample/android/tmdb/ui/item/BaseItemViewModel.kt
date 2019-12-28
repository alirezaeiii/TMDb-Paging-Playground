package com.sample.android.tmdb.ui.item

import com.sample.android.tmdb.ui.TmdbViewModel

abstract class BaseItemViewModel<T> : TmdbViewModel<T>() {

    init {
        showQuery("")
    }
}
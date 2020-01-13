package com.sample.android.tmdb.ui.item

import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.TmdbViewModel

abstract class BaseItemViewModel<T : TmdbItem> : TmdbViewModel<T>() {

    init {
        showQuery("")
    }
}
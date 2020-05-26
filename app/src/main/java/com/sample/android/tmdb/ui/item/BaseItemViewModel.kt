package com.sample.android.tmdb.ui.item

import android.app.Application
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.TmdbViewModel

abstract class BaseItemViewModel<T : TmdbItem>(app: Application) : TmdbViewModel<T>(app = app) {

    init {
        showQuery("")
    }
}
package com.sample.android.tmdb.ui.item

import com.sample.android.tmdb.ui.ItemViewModel

abstract class BaseItemViewModel<T> : ItemViewModel<T>() {

    init {
        showQuery("")
    }
}
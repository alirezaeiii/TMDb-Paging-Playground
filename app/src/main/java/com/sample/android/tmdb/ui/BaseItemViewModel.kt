package com.sample.android.tmdb.ui

abstract class BaseItemViewModel<T> : ItemViewModel<T>() {

    init {
        showQuery("")
    }
}
package com.sample.android.tmdb.ui.paging

import android.os.Parcelable

interface TmdbClickCallback<T : Parcelable> {
    fun onClick(t: T)
}
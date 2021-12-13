package com.sample.android.tmdb.ui.paging

import android.os.Parcelable
import android.widget.ImageView

interface TmdbClickCallback<T : Parcelable> {
    fun onClick(t: T, poster: ImageView)
}
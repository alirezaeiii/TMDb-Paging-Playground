package com.sample.android.tmdb.ui

import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView

interface TmdbClickCallback<T : Parcelable> {
    fun onClick(t: T, poster: ImageView, name: TextView)
}
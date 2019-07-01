package com.sample.android.tmdb.ui

import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView

interface ItemClickCallback<E : Parcelable> {
    fun onClick(e: E, poster: ImageView, name: TextView)
}
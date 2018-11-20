package com.sample.android.tmdb.ui

import android.widget.ImageView
import android.widget.TextView
import com.sample.android.tmdb.vo.Movie

interface MovieClickCallback {
    fun onClick(movie: Movie, poster : ImageView, name : TextView)
}
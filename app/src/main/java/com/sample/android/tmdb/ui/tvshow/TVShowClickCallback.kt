package com.sample.android.tmdb.ui.tvshow

import android.widget.ImageView
import android.widget.TextView
import com.sample.android.tmdb.vo.TVShow

interface TVShowClickCallback {
    fun onClick(tvShow: TVShow, poster: ImageView, name: TextView)
}
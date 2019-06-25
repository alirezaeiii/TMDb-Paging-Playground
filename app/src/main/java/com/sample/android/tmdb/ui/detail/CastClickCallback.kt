package com.sample.android.tmdb.ui.detail

import android.widget.ImageView
import android.widget.TextView

interface CastClickCallback {
    fun onClick(personId: Int, personName: String, profilePath: String?)
}
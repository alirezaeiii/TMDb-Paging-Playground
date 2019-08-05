package com.sample.android.tmdb.ui.detail

interface CastClickCallback {
    fun onClick(personId: Int, personName: String, profilePath: String?)
}
package com.sample.android.tmdb.ui.detail

interface CreditClickCallback {
    fun onClick(personId: Any, personName: String, profilePath: String?)
}
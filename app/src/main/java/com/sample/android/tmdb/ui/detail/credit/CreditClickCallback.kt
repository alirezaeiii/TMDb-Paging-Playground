package com.sample.android.tmdb.ui.detail.credit

interface CreditClickCallback {
    fun onClick(personId: Any, personName: String, profilePath: String?)
}
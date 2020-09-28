package com.sample.android.tmdb.domain

import android.os.Parcelable

interface Credit : Parcelable {
    val id: Any
    val credit: String
    val name: String
    val profilePath: String?
}
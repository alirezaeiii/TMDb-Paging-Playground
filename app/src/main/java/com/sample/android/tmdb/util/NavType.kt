package com.sample.android.tmdb.util

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class NavType : Parcelable {
    MOVIES,
    TV_SERIES
}
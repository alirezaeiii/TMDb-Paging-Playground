package com.sample.android.tmdb.ui.person

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PersonExtra(
        val personId: Int,
        val personName: String,
        val profilePath: String?,
        val backdropPath: String?) : Parcelable
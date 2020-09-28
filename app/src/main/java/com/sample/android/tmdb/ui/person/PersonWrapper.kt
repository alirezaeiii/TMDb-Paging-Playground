package com.sample.android.tmdb.ui.person

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
class PersonWrapper(
        val personId: @RawValue Any,
        val personName: String,
        val profilePath: String?,
        val backdropPath: String?) : Parcelable
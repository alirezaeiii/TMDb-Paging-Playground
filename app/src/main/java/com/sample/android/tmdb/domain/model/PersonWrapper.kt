package com.sample.android.tmdb.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PersonWrapper(
    val credit : Credit,
    val backdropPath: String?) : Parcelable
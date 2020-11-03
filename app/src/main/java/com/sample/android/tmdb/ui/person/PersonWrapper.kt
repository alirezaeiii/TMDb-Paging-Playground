package com.sample.android.tmdb.ui.person

import android.os.Parcelable
import com.sample.android.tmdb.domain.Credit
import kotlinx.android.parcel.Parcelize

@Parcelize
class PersonWrapper(
        val credit : Credit,
        val backdropPath: String?) : Parcelable
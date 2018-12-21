package com.sample.android.tmdb.vo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Cast(@SerializedName("cast_id")
           var castId: Int,
           var character: String,
           var gender: Int,
           @SerializedName("credit_id")
           var creditId: String,
           var name: String,
           @SerializedName("profile_path")
           var profilePath: String?,
           var id: Int,
           var order: Int) : Parcelable
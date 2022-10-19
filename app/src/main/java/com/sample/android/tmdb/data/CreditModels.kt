package com.sample.android.tmdb.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


interface Credit : Parcelable {
    val id: Any
    val credit: String
    val name: String
    val profilePath: String?
}

private const val PROFILE_PATH = "profile_path"

@Parcelize
class Cast(
        @SerializedName("character")
        override val credit: String,
        override val name: String,
        @SerializedName(PROFILE_PATH)
        override val profilePath: String?,
        override val id: Int) : Credit

@Parcelize
class Crew(
        @SerializedName("job")
        override val credit: String,
        override val name: String,
        @SerializedName(PROFILE_PATH)
        override val profilePath: String?,
        override val id: String) : Credit
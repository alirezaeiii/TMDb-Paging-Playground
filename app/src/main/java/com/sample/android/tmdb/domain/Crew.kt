package com.sample.android.tmdb.domain

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Crew(
        @SerializedName("job")
        override val credit: String,
        override val name: String,
        @SerializedName("profile_path")
        override val profilePath: String?,
        override val id: String) : Credit
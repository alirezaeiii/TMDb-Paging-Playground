package com.sample.android.tmdb.domain

import com.google.gson.annotations.SerializedName

class Cast(
        val character: String,
        val name: String,
        @SerializedName("profile_path")
        val profilePath: String?,
        val id: Int)
package com.sample.android.tmdb.vo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
        val id: String,
        val overview: String,
        @SerializedName("release_date")
        var releaseDate: String,
        @SerializedName("poster_path")
        var posterPath: String?,
        @SerializedName("backdrop_path")
        var backdropPath: String?,
        var title: String,
        @SerializedName("vote_average")
        var voteAverage: Double) : Parcelable
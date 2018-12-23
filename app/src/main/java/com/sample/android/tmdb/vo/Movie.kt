package com.sample.android.tmdb.vo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Movie(
        val id: String,
        val overview: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        val title: String,
        @SerializedName("vote_average")
        val voteAverage: Double) : Parcelable
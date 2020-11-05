package com.sample.android.tmdb.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

interface TmdbItem : Parcelable {
    val id : Int
    val overview: String
    val releaseDate: String?
    val posterPath: String?
    val backdropPath: String?
    val name: String
    val voteAverage: Double
}

@Parcelize
class Movie(
        override val id: Int,
        override val overview: String,
        @SerializedName("release_date")
        override val releaseDate: String?,
        @SerializedName("poster_path")
        override val posterPath: String?,
        @SerializedName("backdrop_path")
        override val backdropPath: String?,
        @SerializedName("title")
        override val name: String,
        @SerializedName("vote_average")
        override val voteAverage: Double) : TmdbItem

@Parcelize
class TVShow(
        override val id: Int,
        override val overview: String,
        @SerializedName("first_air_date")
        override val releaseDate: String?,
        @SerializedName("poster_path")
        override val posterPath: String?,
        @SerializedName("backdrop_path")
        override val backdropPath: String?,
        override val name: String,
        @SerializedName("vote_average")
        override val voteAverage: Double) : TmdbItem
package com.sample.android.tmdb.data

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

private const val POSTER_PATH = "poster_path"
private const val BACKDROP_PATH = "backdrop_path"
private const val VOTE_AVERAGE = "vote_average"

@Parcelize
data class Movie(
        override val id: Int,
        override val overview: String,
        @SerializedName("release_date")
        override val releaseDate: String?,
        @SerializedName(POSTER_PATH)
        override val posterPath: String?,
        @SerializedName(BACKDROP_PATH)
        override val backdropPath: String?,
        @SerializedName("title")
        override val name: String,
        @SerializedName(VOTE_AVERAGE)
        override val voteAverage: Double) : TmdbItem

@Parcelize
data class TVShow(
        override val id: Int,
        override val overview: String,
        @SerializedName("first_air_date")
        override val releaseDate: String?,
        @SerializedName(POSTER_PATH)
        override val posterPath: String?,
        @SerializedName(BACKDROP_PATH)
        override val backdropPath: String?,
        override val name: String,
        @SerializedName(VOTE_AVERAGE)
        override val voteAverage: Double) : TmdbItem
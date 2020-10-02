package com.sample.android.tmdb.domain

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

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
package com.sample.android.tmdb.vo

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class TVShow(
        override val id: Int,
        override val overview: String,
        @SerializedName("first_air_date")
        val firstAirDate: String,
        @SerializedName("poster_path")
        override val posterPath: String?,
        @SerializedName("backdrop_path")
        override val backdropPath: String?,
        val name: String,
        @SerializedName("vote_average")
        override val voteAverage: Double) : TmdbItem
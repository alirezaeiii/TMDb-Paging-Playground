package com.sample.android.tmdb.vo

interface TmdbItem {

    val id : Int

    val overview: String

    val posterPath: String?

    val backdropPath: String?

    val voteAverage: Double
}
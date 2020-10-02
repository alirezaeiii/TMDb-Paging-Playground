package com.sample.android.tmdb.domain

import android.os.Parcelable

interface TmdbItem : Parcelable {
    val id : Int
    val overview: String
    val releaseDate: String?
    val posterPath: String?
    val backdropPath: String?
    val name: String
    val voteAverage: Double
}
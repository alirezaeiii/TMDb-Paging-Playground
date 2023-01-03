package com.sample.android.tmdb.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

interface TmdbItem : Parcelable {
    val id: Int
    val overview: String
    val releaseDate: String?
    val posterUrl: String?
    val backdropUrl: String?
    val name: String
    val voteAverage: Double
}

@Parcelize
data class Movie(
    override val id: Int,
    override val overview: String,
    override val releaseDate: String?,
    override val posterUrl: String?,
    override val backdropUrl: String?,
    override val name: String,
    override val voteAverage: Double
) : TmdbItem

@Parcelize
data class TVShow(
    override val id: Int,
    override val overview: String,
    override val releaseDate: String?,
    override val posterUrl: String?,
    override val backdropUrl: String?,
    override val name: String,
    override val voteAverage: Double
) : TmdbItem
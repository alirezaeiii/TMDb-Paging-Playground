package com.sample.android.tmdb.domain

import android.os.Parcelable
import com.sample.android.tmdb.ui.paging.main.SortType
import kotlinx.android.parcel.Parcelize

interface TmdbItem : Parcelable {
    val id: Int
    val overview: String
    val releaseDate: String?
    val posterPath: String?
    val backdropPath: String?
    val name: String
    val voteAverage: Double
}

@Parcelize
data class Movie(
    override val id: Int,
    override val overview: String,
    override val releaseDate: String?,
    override val posterPath: String?,
    override val backdropPath: String?,
    override val name: String,
    override val voteAverage: Double
) : TmdbItem

@Parcelize
data class TVShow(
    override val id: Int,
    override val overview: String,
    override val releaseDate: String?,
    override val posterPath: String?,
    override val backdropPath: String?,
    override val name: String,
    override val voteAverage: Double
) : TmdbItem

class FeedWrapper<T : TmdbItem>(
    val feeds: List<T>,
    val sortTypeResourceId: Int,
    val sortType: SortType
)
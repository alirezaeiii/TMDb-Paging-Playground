package com.sample.android.tmdb.data

import com.google.gson.annotations.SerializedName
import com.sample.android.tmdb.domain.model.Movie
import com.sample.android.tmdb.domain.model.TVShow

interface NetworkTmdbItem {
    val id: Int
    val overview: String
    val releaseDate: String?
    val posterPath: String?
    val backdropPath: String?
    val name: String
    val voteAverage: Double
}

class NetworkMovie(
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
    override val voteAverage: Double
) : NetworkTmdbItem

class NetworkTVShow(
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
    override val voteAverage: Double
) : NetworkTmdbItem

fun List<NetworkMovie>.asMovieDomainModel(): List<Movie> =
    map {
        Movie(
            it.id,
            it.overview,
            it.releaseDate,
            it.posterPath,
            it.backdropPath,
            it.name,
            it.voteAverage
        )
    }

fun List<NetworkTVShow>.asTVShowDomainModel(): List<TVShow> =
    map {
        TVShow(
            it.id,
            it.overview,
            it.releaseDate,
            it.posterPath,
            it.backdropPath,
            it.name,
            it.voteAverage
        )
    }

private const val POSTER_PATH = "poster_path"
private const val BACKDROP_PATH = "backdrop_path"
private const val VOTE_AVERAGE = "vote_average"

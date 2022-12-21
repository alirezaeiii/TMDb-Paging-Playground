package com.sample.android.tmdb.data

import com.google.gson.annotations.SerializedName
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.domain.TVShow
import io.reactivex.Observable

class ItemWrapper<T : NetworkTmdbItem>(
    @SerializedName("results")
    val items: List<T>
)

class VideoWrapper(
    @SerializedName("results")
    val videos: List<Video>
)

class CreditWrapper(
    val cast: List<NetworkCast>,
    val crew: List<NetworkCrew>
)

fun Observable<ItemWrapper<NetworkMovie>>.asMovieDomainModel(): Observable<List<Movie>> =
    this.map { it.items.asMovieDomainModel() }

fun Observable<ItemWrapper<NetworkTVShow>>.asTVShowDomainModel(): Observable<List<TVShow>> =
    this.map { it.items.asTVShowDomainModel() }
package com.sample.android.tmdb.data.response

import com.google.gson.annotations.SerializedName
import com.sample.android.tmdb.domain.model.Movie
import com.sample.android.tmdb.domain.model.TVShow
import io.reactivex.Observable

class ItemWrapper<T : NetworkTmdbItem>(
    @SerializedName("results")
    val items: List<T>
)

class VideoWrapper(
    @SerializedName("results")
    val videos: List<VideoDto>
)

class NetworkCreditWrapper(
    val cast: List<NetworkCast>,
    val crew: List<NetworkCrew>
)

fun Observable<ItemWrapper<NetworkMovie>>.asMovieDomainModel(): Observable<List<Movie>> =
    map { it.items.asMovieDomainModel() }

fun Observable<ItemWrapper<NetworkTVShow>>.asTVShowDomainModel(): Observable<List<TVShow>> =
    map { it.items.asTVShowDomainModel() }
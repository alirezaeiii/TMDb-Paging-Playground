package com.sample.android.tmdb.data.response

import com.google.gson.annotations.SerializedName
import com.sample.android.tmdb.domain.model.CreditWrapper
import io.reactivex.Single

class ItemWrapper<T : NetworkTmdbItem>(
    @SerializedName("results")
    val items: List<T>
)

class VideoWrapper(
    @SerializedName("results")
    val videos: List<VideoResponse>
)

class NetworkCreditWrapper(
    val cast: List<NetworkCast>,
    val crew: List<NetworkCrew>
)

fun Single<NetworkCreditWrapper>.asCreditWrapper(): CreditWrapper {
    val cast = map { it.cast.asCastDomainModel() }
    val crew = map { it.crew.asCrewDomainModel() }
    return CreditWrapper(cast, crew)
}
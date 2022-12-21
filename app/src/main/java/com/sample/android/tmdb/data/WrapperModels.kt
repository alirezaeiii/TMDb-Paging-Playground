package com.sample.android.tmdb.data

import com.google.gson.annotations.SerializedName

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
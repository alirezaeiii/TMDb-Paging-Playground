package com.sample.android.tmdb.data.response

import com.google.gson.annotations.SerializedName

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
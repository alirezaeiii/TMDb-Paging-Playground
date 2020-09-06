package com.sample.android.tmdb.domain

import com.google.gson.annotations.SerializedName

class ItemWrapper<T : TmdbItem>(
        @SerializedName("results")
        val items: List<T>
)

class VideoWrapper(
        @SerializedName("results")
        val videos: List<Video>
)

class CastWrapper(
        @SerializedName("cast")
        val cast: List<Cast>
)
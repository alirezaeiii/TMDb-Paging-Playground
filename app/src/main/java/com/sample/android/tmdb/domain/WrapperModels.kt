package com.sample.android.tmdb.domain

import com.google.gson.annotations.SerializedName
import com.sample.android.tmdb.ui.paging.main.SortType

class ItemWrapper<T : TmdbItem>(
    @SerializedName("results")
    val items: List<T>
)

class VideoWrapper(
    @SerializedName("results")
    val videos: List<Video>
)

class CreditWrapper(
    val cast: List<Cast>,
    val crew: List<Crew>
)

class FeedWrapper<T : TmdbItem>(
    val feeds: List<T>,
    val sortTypeResourceId: Int,
    val sortType: SortType
)
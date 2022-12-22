package com.sample.android.tmdb.domain.model

class FeedWrapper<T : TmdbItem>(
    val feeds: List<T>,
    val sortTypeResourceId: Int,
    val sortType: SortType
)
package com.sample.android.tmdb.domain.model

import androidx.annotation.StringRes

class FeedWrapper(
    val feeds: List<TmdbItem>,
    @StringRes val sortTypeResourceId: Int,
    val sortType: SortType
)
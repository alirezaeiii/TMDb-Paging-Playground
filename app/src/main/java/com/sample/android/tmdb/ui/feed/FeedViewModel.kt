package com.sample.android.tmdb.ui.feed

import com.sample.android.tmdb.domain.model.FeedWrapper
import com.sample.android.tmdb.domain.model.TmdbItem
import com.sample.android.tmdb.domain.repository.BaseFeedRepository
import com.sample.android.tmdb.ui.BaseViewModel

open class FeedViewModel<T : TmdbItem>(
    repository: BaseFeedRepository<T>,
) : BaseViewModel<List<FeedWrapper<T>>>(repository)
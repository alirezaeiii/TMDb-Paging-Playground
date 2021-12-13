package com.sample.android.tmdb.ui.feed

import com.sample.android.tmdb.domain.TmdbItem
import dagger.android.support.DaggerFragment

abstract class FeedFragment<T: TmdbItem>: DaggerFragment() {

    protected abstract val viewModel: FeedViewModel<T>
}
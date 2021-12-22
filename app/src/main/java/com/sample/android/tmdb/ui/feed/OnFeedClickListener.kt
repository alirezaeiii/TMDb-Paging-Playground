package com.sample.android.tmdb.ui.feed

import com.sample.android.tmdb.domain.TmdbItem

class OnFeedClickListener<T : TmdbItem>(val clickListener: (item: T) -> Unit) {
    fun onClick(item: T) =
        clickListener(item)
}
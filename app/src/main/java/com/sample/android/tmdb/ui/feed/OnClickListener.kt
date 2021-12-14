package com.sample.android.tmdb.ui.feed

import com.sample.android.tmdb.domain.TmdbItem

class OnClickListener(val clickListener: (item: TmdbItem) -> Unit) {
    fun onClick(item: TmdbItem) =
        clickListener(item)
}
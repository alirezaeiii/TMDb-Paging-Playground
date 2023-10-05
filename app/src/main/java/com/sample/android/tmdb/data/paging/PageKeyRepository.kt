package com.sample.android.tmdb.data.paging

import com.sample.android.tmdb.domain.model.TmdbItem

interface PageKeyRepository<T : TmdbItem> {
    fun getItems(): Listing<T>
}
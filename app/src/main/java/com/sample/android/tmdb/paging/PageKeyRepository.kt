package com.sample.android.tmdb.paging

import com.sample.android.tmdb.domain.TmdbItem

interface PageKeyRepository<T : TmdbItem> {
    fun getItems(): Listing<T>
}
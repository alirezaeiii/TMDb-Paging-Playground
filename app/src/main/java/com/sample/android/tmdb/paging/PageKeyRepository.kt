package com.sample.android.tmdb.paging

import com.sample.android.tmdb.data.TmdbItem

interface PageKeyRepository<T : TmdbItem> {
    fun getItems(): Listing<T>
}
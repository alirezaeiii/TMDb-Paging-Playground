package com.sample.android.tmdb.repository

import com.sample.android.tmdb.domain.TmdbItem

interface ItemRepository<T : TmdbItem> {
    fun getItems(query: String, pageSize: Int): Listing<T>
}
package com.sample.android.tmdb.repository

import com.sample.android.tmdb.domain.TmdbItem
import java.util.concurrent.Executor

interface ItemRepository<T : TmdbItem> {
    fun getItems(query: String, networkExecutor: Executor): Listing<T>
}
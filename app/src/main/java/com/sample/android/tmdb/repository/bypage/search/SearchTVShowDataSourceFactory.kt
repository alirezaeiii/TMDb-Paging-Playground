package com.sample.android.tmdb.repository.bypage.search

import android.content.Context
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import java.util.concurrent.Executor

class SearchTVShowDataSourceFactory(
        private val api: TVShowApi,
        private val query: String,
        private val retryExecutor: Executor,
        private val context: Context)
    : ItemDataSourceFactory<TVShow>() {

    override fun getDataSource(): PageKeyedItemDataSource<TVShow> =
            PageKeyedSearchTVShowDataSource(api = api,
                    query = query,
                    retryExecutor = retryExecutor,
                    context = context)
}
package com.sample.android.tmdb.paging.search.tvshow

import android.content.Context
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.paging.TmdbDataSourceFactory
import com.sample.android.tmdb.paging.PageKeyedItemDataSource
import java.util.concurrent.Executor

class SearchTVShowDataSourceFactory(
        private val api: TVShowApi,
        private val query: String,
        private val retryExecutor: Executor,
        private val context: Context)
    : TmdbDataSourceFactory<TVShow>() {

    override fun getDataSource(): PageKeyedItemDataSource<TVShow> =
            PageKeyedSearchTVShowDataSource(api = api,
                    query = query,
                    retryExecutor = retryExecutor,
                    context = context)
}
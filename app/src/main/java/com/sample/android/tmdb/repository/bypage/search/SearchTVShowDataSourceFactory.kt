package com.sample.android.tmdb.repository.bypage.search

import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import java.util.concurrent.Executor

class SearchTVShowDataSourceFactory(
        private val api: TmdbApi,
        private val query: String,
        private val retryExecutor: Executor)
    : ItemDataSourceFactory<TVShow, TmdbApi.TVShowWrapper>() {

    override fun getDataSource(): PageKeyedItemDataSource<TVShow, TmdbApi.TVShowWrapper> =
            PageKeyedSearchTVShowDataSource(api = api,
                    query = query,
                    retryExecutor = retryExecutor)
}
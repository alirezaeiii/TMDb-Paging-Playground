package com.sample.android.tmdb.paging.search.tvshow

import android.content.Context
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.paging.TmdbDataSourceFactory
import com.sample.android.tmdb.paging.PageKeyedItemDataSource
import java.util.concurrent.Executor

class SearchTVShowDataSourceFactory(
        api: TVShowApi,
        query: String,
        retryExecutor: Executor,
        context: Context
) : TmdbDataSourceFactory<TVShow>() {

    override val dataSource: PageKeyedItemDataSource<TVShow> by lazy {
        PageKeyedSearchTVShowDataSource(api = api,
                query = query,
                retryExecutor = retryExecutor,
                context = context)
    }
}
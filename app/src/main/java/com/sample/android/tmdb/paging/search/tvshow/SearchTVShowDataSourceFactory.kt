package com.sample.android.tmdb.paging.search.tvshow

import android.content.Context
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.paging.BaseDataSourceFactory
import com.sample.android.tmdb.paging.BasePageKeyedDataSource
import java.util.concurrent.Executor

class SearchTVShowDataSourceFactory(
    private val api: TVShowApi,
    private val query: String,
    private val retryExecutor: Executor,
    private val context: Context
) : BaseDataSourceFactory<TVShow>() {

    override fun getDataSource(): BasePageKeyedDataSource<TVShow> =
        SearchTVShowPageKeyedDataSource(
            api = api,
            query = query,
            retryExecutor = retryExecutor,
            context = context
        )
}
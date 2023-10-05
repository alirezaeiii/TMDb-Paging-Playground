package com.sample.android.tmdb.data.paging.search.tvshow

import android.content.Context
import com.sample.android.tmdb.domain.model.TVShow
import com.sample.android.tmdb.data.network.TVShowService
import com.sample.android.tmdb.data.paging.BaseDataSourceFactory
import com.sample.android.tmdb.data.paging.BasePageKeyedDataSource
import java.util.concurrent.Executor

class SearchTVShowDataSourceFactory(
    private val api: TVShowService,
    private val query: String,
    private val retryExecutor: Executor,
    private val context: Context
) : BaseDataSourceFactory<TVShow>() {

    override val dataSource: BasePageKeyedDataSource<TVShow>
        get() = SearchTVShowPageKeyedDataSource(
            api = api,
            query = query,
            retryExecutor = retryExecutor,
            context = context
        )
}
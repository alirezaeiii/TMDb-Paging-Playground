package com.sample.android.tmdb.data.paging.tvshow

import android.content.Context
import com.sample.android.tmdb.domain.model.TVShow
import com.sample.android.tmdb.data.network.TVShowService
import com.sample.android.tmdb.domain.paging.BaseDataSourceFactory
import com.sample.android.tmdb.domain.paging.BasePageKeyedDataSource
import com.sample.android.tmdb.domain.model.SortType
import java.util.concurrent.Executor

class TVShowsDataSourceFactory(
    private val api: TVShowService,
    private val sortType: SortType,
    private val retryExecutor: Executor,
    private val context: Context
) : BaseDataSourceFactory<TVShow>() {

    override val dataSource: BasePageKeyedDataSource<TVShow>
        get() = TVShowsPageKeyedDataSource(
            api = api,
            sortType = sortType,
            retryExecutor = retryExecutor,
            context = context
        )
}
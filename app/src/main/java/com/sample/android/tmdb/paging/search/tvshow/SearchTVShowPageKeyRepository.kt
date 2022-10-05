package com.sample.android.tmdb.paging.search.tvshow

import android.content.Context
import com.sample.android.tmdb.data.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.paging.BaseDataSourceFactory
import com.sample.android.tmdb.paging.BasePageKeyRepository
import java.util.concurrent.Executor

class SearchTVShowPageKeyRepository(
    api: TVShowApi,
    query: String,
    retryExecutor: Executor,
    context: Context
) : BasePageKeyRepository<TVShow>(retryExecutor) {

    override val sourceFactory: BaseDataSourceFactory<TVShow> =
        SearchTVShowDataSourceFactory(
            api = api,
            query = query,
            retryExecutor = retryExecutor,
            context = context
        )
}
package com.sample.android.tmdb.data.paging.search.tvshow

import android.content.Context
import com.sample.android.tmdb.domain.model.TVShow
import com.sample.android.tmdb.data.network.TVShowService
import com.sample.android.tmdb.data.paging.BaseDataSourceFactory
import com.sample.android.tmdb.data.paging.BasePageKeyRepository
import java.util.concurrent.Executor

class SearchTVShowPageKeyRepository(
    api: TVShowService,
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
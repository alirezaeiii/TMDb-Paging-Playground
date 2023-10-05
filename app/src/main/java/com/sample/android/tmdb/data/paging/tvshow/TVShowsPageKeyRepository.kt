package com.sample.android.tmdb.data.paging.tvshow

import android.content.Context
import com.sample.android.tmdb.domain.model.TVShow
import com.sample.android.tmdb.data.network.TVShowService
import com.sample.android.tmdb.data.paging.BaseDataSourceFactory
import com.sample.android.tmdb.data.paging.BasePageKeyRepository
import com.sample.android.tmdb.domain.model.SortType
import java.util.concurrent.Executor

class TVShowsPageKeyRepository(
    api: TVShowService,
    sortType: SortType,
    retryExecutor: Executor,
    context: Context
) : BasePageKeyRepository<TVShow>(retryExecutor) {

    override val sourceFactory: BaseDataSourceFactory<TVShow> =
        TVShowsDataSourceFactory(
            api = api,
            sortType = sortType,
            retryExecutor = retryExecutor,
            context = context
        )
}
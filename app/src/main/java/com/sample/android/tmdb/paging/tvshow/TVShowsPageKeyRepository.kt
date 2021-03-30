package com.sample.android.tmdb.paging.tvshow

import android.content.Context
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.paging.BaseDataSourceFactory
import com.sample.android.tmdb.paging.BasePageKeyRepository
import com.sample.android.tmdb.ui.item.SortType
import java.util.concurrent.Executor

class TVShowsPageKeyRepository(
        private val api: TVShowApi,
        private val sortType: SortType,
        private val context: Context)
    : BasePageKeyRepository<TVShow>() {

    override fun getSourceFactory(retryExecutor: Executor): BaseDataSourceFactory<TVShow> =
            TVShowsDataSourceFactory(api = api,
                    sortType = sortType,
                    retryExecutor = retryExecutor,
                    context = context)
}
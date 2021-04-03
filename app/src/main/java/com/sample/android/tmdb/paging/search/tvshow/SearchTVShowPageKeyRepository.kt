package com.sample.android.tmdb.paging.search.tvshow

import android.content.Context
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.paging.BaseDataSourceFactory
import com.sample.android.tmdb.paging.BasePageKeyRepository
import java.util.concurrent.Executor

class SearchTVShowPageKeyRepository(private val api: TVShowApi,
                                    private val query : String,
                                    private val retryExecutor : Executor,
                                    private val context: Context)
    : BasePageKeyRepository<TVShow>(retryExecutor) {

    override fun getSourceFactory(): BaseDataSourceFactory<TVShow> =
            SearchTVShowDataSourceFactory(api = api,
                    query = query,
                    retryExecutor = retryExecutor,
                    context = context)
}
package com.sample.android.tmdb.paging.search.tvshow

import android.content.Context
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.paging.TmdbDataSourceFactory
import com.sample.android.tmdb.paging.TmdbPageKeyRepository
import java.util.concurrent.Executor

class SearchTVShowPageKeyRepository(private val api: TVShowApi,
                                    private val query : String,
                                    private val context: Context)
    : TmdbPageKeyRepository<TVShow>() {

    override fun getSourceFactory(retryExecutor: Executor): TmdbDataSourceFactory<TVShow> =
            SearchTVShowDataSourceFactory(api = api,
                    query = query,
                    retryExecutor = retryExecutor,
                    context = context)
}
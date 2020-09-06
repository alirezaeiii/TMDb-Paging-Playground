package com.sample.android.tmdb.repository.bypage.search

import android.content.Context
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyRepository
import java.util.concurrent.Executor

class SearchTVShowPageKeyRepository(private val api: TVShowApi,
                                    private val context: Context)
    : PageKeyRepository<TVShow>() {

    override fun getSourceFactory(query: String, retryExecutor: Executor): ItemDataSourceFactory<TVShow> =
            SearchTVShowDataSourceFactory(api = api,
                    query = query,
                    retryExecutor = retryExecutor,
                    context = context)
}
package com.sample.android.tmdb.repository.bypage.search

import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyRepository
import java.util.concurrent.Executor

class SearchTVShowPageKeyRepository(private val api: TmdbApi)
    : PageKeyRepository<TVShow, TmdbApi.TVShowWrapper>() {

    override fun getSourceFactory(query: String, retryExecutor: Executor): ItemDataSourceFactory<TVShow, TmdbApi.TVShowWrapper> =
            SearchTVShowDataSourceFactory(api = api,
                    query = query,
                    retryExecutor = retryExecutor)
}
package com.sample.android.tmdb.repository.bypage.search

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.repository.RemoteDataSource
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyRepository
import java.util.concurrent.Executor

class SearchTVShowPageKeyRepository(private val dataSource: RemoteDataSource)
    : PageKeyRepository<TVShow, ItemApi.TVShowWrapper>() {

    override fun getSourceFactory(query: String, retryExecutor: Executor): ItemDataSourceFactory<TVShow, ItemApi.TVShowWrapper> =
            SearchTVShowDataSourceFactory(dataSource = dataSource,
                    query = query,
                    retryExecutor = retryExecutor)
}
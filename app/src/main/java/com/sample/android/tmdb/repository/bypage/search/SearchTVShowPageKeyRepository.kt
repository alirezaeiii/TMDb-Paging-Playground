package com.sample.android.tmdb.repository.bypage.search

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.repository.RemoteDataSource
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyRepository
import java.util.concurrent.Executor

class SearchTVShowPageKeyRepository(private val dataSource: RemoteDataSource,
                                    private val networkExecutor: Executor)
    : PageKeyRepository<TVShow, ItemApi.TVShowWrapper>(networkExecutor) {

    override fun getSourceFactory(query: String): ItemDataSourceFactory<TVShow, ItemApi.TVShowWrapper> =
            SearchTVShowDataSourceFactory(dataSource = dataSource,
                    query = query,
                    retryExecutor = networkExecutor)
}
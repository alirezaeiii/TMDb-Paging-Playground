package com.sample.android.tmdb.repository.bypage.search

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.repository.RemoteDataSource
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import java.util.concurrent.Executor

class SearchTVShowDataSourceFactory(
        dataSource: RemoteDataSource,
        query: String,
        retryExecutor: Executor)
    : ItemDataSourceFactory<TVShow, ItemApi.TVShowWrapper>() {

    override val source: PageKeyedItemDataSource<TVShow, ItemApi.TVShowWrapper> =
            PageKeyedSearchTVShowDataSource(dataSource = dataSource,
                    query = query,
                    retryExecutor = retryExecutor)
}
package com.sample.android.tmdb.repository.bypage.tvshow

import com.sample.android.tmdb.util.SortType
import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.repository.RemoteDataSource
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import com.sample.android.tmdb.domain.TVShow
import java.util.concurrent.Executor

class TVShowsDataSourceFactory(
        dataSource: RemoteDataSource,
        sortType: SortType,
        retryExecutor: Executor)
    : ItemDataSourceFactory<TVShow, ItemApi.TVShowWrapper>() {

    override val source: PageKeyedItemDataSource<TVShow, ItemApi.TVShowWrapper> =
            PageKeyedTVShowsDataSource(dataSource = dataSource,
                    sortType = sortType,
                    retryExecutor = retryExecutor)
}
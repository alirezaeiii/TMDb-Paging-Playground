package com.sample.android.tmdb.repository.bypage.tvshow

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.repository.RemoteDataSource
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyRepository
import com.sample.android.tmdb.util.SortType
import java.util.concurrent.Executor

class TVShowsPageKeyRepository(
        private val dataSource: RemoteDataSource,
        private val sortType: SortType)
    : PageKeyRepository<TVShow, ItemApi.TVShowWrapper>() {

    override fun getSourceFactory(query: String, retryExecutor: Executor): ItemDataSourceFactory<TVShow, ItemApi.TVShowWrapper> =
            TVShowsDataSourceFactory(dataSource = dataSource,
                    sortType = sortType,
                    retryExecutor = retryExecutor)
}
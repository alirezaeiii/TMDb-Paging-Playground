package com.sample.android.tmdb.repository.bypage.tvshow

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import com.sample.android.tmdb.usecase.UseCase
import com.sample.android.tmdb.util.SortType
import java.util.concurrent.Executor

class TVShowsDataSourceFactory(
        private val useCase: UseCase,
        private val sortType: SortType,
        private val retryExecutor: Executor)
    : ItemDataSourceFactory<TVShow, ItemApi.TVShowWrapper>() {

    override fun getDataSource(): PageKeyedItemDataSource<TVShow, ItemApi.TVShowWrapper> =
            PageKeyedTVShowsDataSource(useCase = useCase,
                    sortType = sortType,
                    retryExecutor = retryExecutor)
}
package com.sample.android.tmdb.repository.bypage.search

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import com.sample.android.tmdb.usecase.SearchUseCase
import java.util.concurrent.Executor

class SearchTVShowDataSourceFactory(
        private val useCase: SearchUseCase,
        private val query: String,
        private val retryExecutor: Executor)
    : ItemDataSourceFactory<TVShow, ItemApi.TVShowWrapper>() {

    override fun getDataSource(): PageKeyedItemDataSource<TVShow, ItemApi.TVShowWrapper> =
            PageKeyedSearchTVShowDataSource(useCase = useCase,
                    query = query,
                    retryExecutor = retryExecutor)
}
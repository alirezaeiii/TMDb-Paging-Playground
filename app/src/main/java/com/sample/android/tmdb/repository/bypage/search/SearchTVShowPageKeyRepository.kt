package com.sample.android.tmdb.repository.bypage.search

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyRepository
import com.sample.android.tmdb.usecase.UseCase
import java.util.concurrent.Executor

class SearchTVShowPageKeyRepository(private val useCase: UseCase)
    : PageKeyRepository<TVShow, ItemApi.TVShowWrapper>() {

    override fun getSourceFactory(query: String, retryExecutor: Executor): ItemDataSourceFactory<TVShow, ItemApi.TVShowWrapper> =
            SearchTVShowDataSourceFactory(useCase = useCase,
                    query = query,
                    retryExecutor = retryExecutor)
}
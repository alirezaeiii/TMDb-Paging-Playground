package com.sample.android.tmdb.repository.bypage.tvshow

import android.content.Context
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyRepository
import com.sample.android.tmdb.util.SortType
import java.util.concurrent.Executor

class TVShowsPageKeyRepository(
        private val api: TmdbApi,
        private val sortType: SortType,
        private val context: Context)
    : PageKeyRepository<TVShow, TmdbApi.TVShowWrapper>() {

    override fun getSourceFactory(query: String, retryExecutor: Executor): ItemDataSourceFactory<TVShow, TmdbApi.TVShowWrapper> =
            TVShowsDataSourceFactory(api = api,
                    sortType = sortType,
                    retryExecutor = retryExecutor,
                    context = context)
}
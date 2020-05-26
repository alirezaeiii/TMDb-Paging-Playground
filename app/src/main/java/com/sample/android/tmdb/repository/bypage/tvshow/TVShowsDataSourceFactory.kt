package com.sample.android.tmdb.repository.bypage.tvshow

import android.content.Context
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import com.sample.android.tmdb.util.SortType
import java.util.concurrent.Executor

class TVShowsDataSourceFactory(
        private val api: TmdbApi,
        private val sortType: SortType,
        private val retryExecutor: Executor,
        private val context: Context)
    : ItemDataSourceFactory<TVShow, TmdbApi.TVShowWrapper>() {

    override fun getDataSource(): PageKeyedItemDataSource<TVShow, TmdbApi.TVShowWrapper> =
            PageKeyedTVShowsDataSource(api = api,
                    sortType = sortType,
                    retryExecutor = retryExecutor,
                    context = context)
}
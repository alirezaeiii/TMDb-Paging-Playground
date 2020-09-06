package com.sample.android.tmdb.repository.bypage.tvshow

import android.content.Context
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import com.sample.android.tmdb.util.SortType
import java.util.concurrent.Executor

class PageKeyedTVShowsDataSource(
        private val api: TVShowApi,
        private val sortType: SortType,
        retryExecutor: Executor,
        context: Context)
    : PageKeyedItemDataSource<TVShow>(retryExecutor, context) {

    override fun fetchItems(page: Int) = when (sortType) {
        SortType.MOST_POPULAR -> api.popularItems(page)
        SortType.HIGHEST_RATED -> api.topRatedItems(page)
        SortType.UPCOMING -> api.latestItems(page)
    }
}
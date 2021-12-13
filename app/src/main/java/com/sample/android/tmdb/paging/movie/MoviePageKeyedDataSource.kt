package com.sample.android.tmdb.paging.movie

import android.content.Context
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.paging.BasePageKeyedDataSource
import com.sample.android.tmdb.ui.paging.main.SortType
import java.util.concurrent.Executor

class MoviePageKeyedDataSource(
    private val api: MovieApi,
    private val sortType: SortType,
    retryExecutor: Executor,
    context: Context)
    : BasePageKeyedDataSource<Movie>(retryExecutor, context) {

    override fun fetchItems(page: Int) = when (sortType) {
        SortType.MOST_POPULAR -> api.popularItems(page)
        SortType.HIGHEST_RATED -> api.topRatedItems(page)
        SortType.UPCOMING -> api.latestItems(page)
    }
}
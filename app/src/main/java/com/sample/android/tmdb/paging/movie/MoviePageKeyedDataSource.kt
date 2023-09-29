package com.sample.android.tmdb.paging.movie

import android.content.Context
import com.sample.android.tmdb.data.network.MovieApi
import com.sample.android.tmdb.data.response.asMovieDomainModel
import com.sample.android.tmdb.domain.model.Movie
import com.sample.android.tmdb.domain.model.SortType
import com.sample.android.tmdb.paging.BasePageKeyedDataSource
import io.reactivex.Observable
import java.util.concurrent.Executor

class MoviePageKeyedDataSource(
    private val api: MovieApi,
    private val sortType: SortType,
    retryExecutor: Executor,
    context: Context
) : BasePageKeyedDataSource<Movie>(retryExecutor, context) {

    override fun fetchItems(page: Int): Observable<List<Movie>> = when (sortType) {
        SortType.MOST_POPULAR -> api.popularMovies(page).map { it.items.asMovieDomainModel() }
        SortType.HIGHEST_RATED -> api.topRatedMovies(page).map { it.items.asMovieDomainModel() }
        SortType.UPCOMING -> api.upcomingMovies(page).map { it.items.asMovieDomainModel() }
        SortType.TRENDING -> api.trendingMovies(page).map { it.items.asMovieDomainModel() }
        SortType.NOW_PLAYING -> api.nowPlayingMovies(page).map { it.items.asMovieDomainModel() }
        SortType.DISCOVER -> api.discoverMovies(page).map { it.items.asMovieDomainModel() }
    }
}
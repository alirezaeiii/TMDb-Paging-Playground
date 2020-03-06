package com.sample.android.tmdb.repository.bypage.movie

import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import com.sample.android.tmdb.util.SortType
import java.util.concurrent.Executor

class MoviesDataSourceFactory(
        private val api: TmdbApi,
        private val sortType: SortType,
        private val retryExecutor: Executor)
    : ItemDataSourceFactory<Movie, TmdbApi.MovieWrapper>() {

    override fun getDataSource(): PageKeyedItemDataSource<Movie, TmdbApi.MovieWrapper> =
            PageKeyedMovieDataSource(api = api,
                    sortType = sortType,
                    retryExecutor = retryExecutor)
}

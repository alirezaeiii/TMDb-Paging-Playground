package com.sample.android.tmdb.repository.bypage.search

import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import java.util.concurrent.Executor

class SearchMovieDataSourceFactory(
        private val api: TmdbApi,
        private val query: String,
        private val retryExecutor: Executor)
    : ItemDataSourceFactory<Movie, TmdbApi.MovieWrapper>() {

    override fun getDataSource(): PageKeyedItemDataSource<Movie, TmdbApi.MovieWrapper> =
            PageKeyedSearchMovieDataSource(api = api,
                    query = query,
                    retryExecutor = retryExecutor)
}
package com.sample.android.tmdb.paging.search.movie

import android.content.Context
import com.sample.android.tmdb.data.asMovieDomainModel
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.paging.BasePageKeyedDataSource
import io.reactivex.Observable
import java.util.concurrent.Executor

class SearchMoviePageKeyedDataSource(
        private val api: MovieApi,
        private val query: String,
        retryExecutor: Executor,
        context: Context
) : BasePageKeyedDataSource<Movie>(retryExecutor, context) {

    override fun fetchItems(page: Int): Observable<List<Movie>> =
        api.searchItems(page, query).asMovieDomainModel()
}
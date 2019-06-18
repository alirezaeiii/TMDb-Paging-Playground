package com.sample.android.tmdb.repository.bypage

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.api.MovieApi
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.vo.Movie
import java.util.concurrent.Executor

class MoviePageKeyRepository(private val dataSource: MoviesRemoteDataSource,
                             private val sortType: SortType?,
                             networkExecutor: Executor) : PageKeyRepository<Movie, MovieApi.MovieWrapper>(networkExecutor) {

    override fun getSourceFactory(query: String): ItemDataSourceFactory<Movie, MovieApi.MovieWrapper> =
            MoviesDataSourceFactory(dataSource = dataSource,
                    sortType = sortType,
                    query = query)
}
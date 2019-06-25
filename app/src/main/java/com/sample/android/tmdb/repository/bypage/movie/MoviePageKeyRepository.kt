package com.sample.android.tmdb.repository.bypage.movie

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyRepository
import com.sample.android.tmdb.vo.Movie
import java.util.concurrent.Executor

class MoviePageKeyRepository(private val dataSource: MoviesRemoteDataSource,
                             private val sortType: SortType?,
                             networkExecutor: Executor) : PageKeyRepository<Movie, ItemApi.MovieWrapper>(networkExecutor) {

    override fun getSourceFactory(query: String): ItemDataSourceFactory<Movie, ItemApi.MovieWrapper> =
            MoviesDataSourceFactory(dataSource = dataSource,
                    sortType = sortType,
                    query = query)
}
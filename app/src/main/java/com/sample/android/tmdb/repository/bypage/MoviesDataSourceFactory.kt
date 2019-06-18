package com.sample.android.tmdb.repository.bypage

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.api.MovieApi
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.vo.Movie

class MoviesDataSourceFactory(
        private val dataSource: MoviesRemoteDataSource,
        private val sortType: SortType?,
        private val query: String) : ItemDataSourceFactory<Movie, MovieApi.MovieWrapper>() {

    override fun getDataSource(): PageKeyedItemDataSource<Movie, MovieApi.MovieWrapper> =
            PageKeyedMovieDataSource(dataSource = dataSource,
                    sortType = sortType,
                    query = query)
}

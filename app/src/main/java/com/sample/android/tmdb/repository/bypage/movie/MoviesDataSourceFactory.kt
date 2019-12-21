package com.sample.android.tmdb.repository.bypage.movie

import com.sample.android.tmdb.util.SortType
import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.repository.RemoteDataSource
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import com.sample.android.tmdb.domain.Movie
import java.util.concurrent.Executor

class MoviesDataSourceFactory(
        dataSource: RemoteDataSource,
        sortType: SortType,
        retryExecutor: Executor)
    : ItemDataSourceFactory<Movie, ItemApi.MovieWrapper>() {

    override val source: PageKeyedItemDataSource<Movie, ItemApi.MovieWrapper> =
            PageKeyedMovieDataSource(dataSource = dataSource,
                    sortType = sortType,
                    retryExecutor = retryExecutor)
}

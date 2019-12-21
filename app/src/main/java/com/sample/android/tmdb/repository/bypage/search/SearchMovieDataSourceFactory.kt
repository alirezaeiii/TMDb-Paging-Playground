package com.sample.android.tmdb.repository.bypage.search

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.repository.RemoteDataSource
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import java.util.concurrent.Executor

class SearchMovieDataSourceFactory(
        dataSource: RemoteDataSource,
        query: String,
        retryExecutor: Executor)
    : ItemDataSourceFactory<Movie, ItemApi.MovieWrapper>() {

    override val source: PageKeyedItemDataSource<Movie, ItemApi.MovieWrapper> =
            PageKeyedSearchMovieDataSource(dataSource = dataSource,
                    query = query,
                    retryExecutor = retryExecutor)
}
package com.sample.android.tmdb.repository.bypage

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.api.MovieApi
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.vo.TVShow
import java.util.concurrent.Executor

class TVShowsPageKeyRepository (private val dataSource: MoviesRemoteDataSource,
                                private val sortType: SortType?,
                                networkExecutor: Executor) : PageKeyRepository<TVShow, MovieApi.TVShowWrapper>(networkExecutor) {

    override fun getSourceFactory(query: String): ItemDataSourceFactory<TVShow, MovieApi.TVShowWrapper> =
            TVShowsDataSourceFactory(dataSource = dataSource,
                    sortType = sortType,
                    query = query)
}
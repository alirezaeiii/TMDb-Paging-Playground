package com.sample.android.tmdb.repository.bypage

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.api.MovieApi
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.vo.TVShow

class TVShowsDataSourceFactory(
        private val dataSource: MoviesRemoteDataSource,
        private val sortType: SortType?,
        private val query: String) : ItemDataSourceFactory<TVShow, MovieApi.TVShowWrapper>() {

    override fun getDataSource(): PageKeyedItemDataSource<TVShow, MovieApi.TVShowWrapper> =
            PageKeyedTVShowsDataSource(dataSource = dataSource,
                    sortType = sortType,
                    query = query)
}
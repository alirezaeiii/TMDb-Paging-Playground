package com.sample.android.tmdb.repository.bypage

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.vo.TVShow

class TVShowsDataSourceFactory(
        private val dataSource: MoviesRemoteDataSource,
        private val sortType: SortType?,
        private val query: String) : ItemDataSourceFactory<TVShow, ItemApi.TVShowWrapper>() {

    override fun getDataSource(): PageKeyedItemDataSource<TVShow, ItemApi.TVShowWrapper> =
            PageKeyedTVShowsDataSource(dataSource = dataSource,
                    sortType = sortType,
                    query = query)
}
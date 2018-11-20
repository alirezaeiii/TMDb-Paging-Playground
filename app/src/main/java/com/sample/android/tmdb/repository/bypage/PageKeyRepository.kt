package com.sample.android.tmdb.repository.bypage

import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.support.annotation.MainThread
import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.repository.Listing
import com.sample.android.tmdb.repository.MovieRepository
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.vo.Movie
import java.util.concurrent.Executor

class PageKeyRepository(private val dataSource: MoviesRemoteDataSource,
                        private val sortType: SortType?,
                        private val networkExecutor: Executor) : MovieRepository {

    @MainThread
    override fun getMovies(query: String, pageSize: Int): Listing<Movie> {

        val sourceFactory = MovieDataSourceFactory(dataSource = dataSource,
                sortType = sortType,
                query = query)

        val livePagedList = LivePagedListBuilder(sourceFactory, pageSize)
                // provide custom executor for network requests, otherwise it will default to
                // Arch Components' IO pool which is also used for disk access
                .setFetchExecutor(networkExecutor)
                .build()

        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.initialLoad
        }
        return Listing(
                pagedList = livePagedList,
                networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                    it.networkState
                },
                refresh = {
                    sourceFactory.sourceLiveData.value?.invalidate()
                },
                refreshState = refreshState
        )
    }
}
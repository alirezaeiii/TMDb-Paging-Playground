package com.sample.android.tmdb.repository.bypage

import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.support.annotation.MainThread
import com.sample.android.tmdb.repository.Listing
import com.sample.android.tmdb.repository.MovieRepository
import java.util.concurrent.Executor

abstract class PageKeyRepository<T, E>(
        private val networkExecutor: Executor) : MovieRepository<T> {

    protected abstract fun getSourceFactory(query: String): ItemDataSourceFactory<T, E>

    @MainThread
    override fun getItems(query: String, pageSize: Int): Listing<T> {

        val sourceFactory = getSourceFactory(query)

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
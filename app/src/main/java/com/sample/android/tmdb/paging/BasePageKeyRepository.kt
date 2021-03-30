package com.sample.android.tmdb.paging

import androidx.lifecycle.Transformations
import androidx.annotation.MainThread
import androidx.paging.LivePagedListBuilder
import com.sample.android.tmdb.domain.TmdbItem
import java.util.concurrent.Executor

abstract class BasePageKeyRepository<T : TmdbItem> : PageKeyRepository<T> {

    protected abstract fun getSourceFactory(retryExecutor: Executor): BaseDataSourceFactory<T>

    @MainThread
    override fun getItems(networkExecutor: Executor): Listing<T> {

        val sourceFactory = getSourceFactory(networkExecutor)

        val livePagedList = LivePagedListBuilder(sourceFactory, PAGE_SIZE)
                // provide custom executor for network requests, otherwise it will default to
                // Arch Components' IO pool which is also used for disk access
                .setFetchExecutor(networkExecutor)
                .build()

        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveDataBase) {
            it.initialLoad
        }

        val networkState = Transformations.switchMap(sourceFactory.sourceLiveDataBase) {
            it.networkState
        }

        return Listing(
                pagedList = livePagedList,
                networkState = networkState,
                refresh = {
                    sourceFactory.sourceLiveDataBase.value?.invalidate()
                },
                refreshState = refreshState,
                retry = {
                    sourceFactory.sourceLiveDataBase.value?.retryAllFailed()
                }
        )
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}
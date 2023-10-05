package com.sample.android.tmdb.data.paging

import androidx.lifecycle.Transformations
import androidx.annotation.MainThread
import androidx.paging.LivePagedListBuilder
import com.sample.android.tmdb.domain.model.TmdbItem
import java.util.concurrent.Executor

abstract class BasePageKeyRepository<T : TmdbItem>(
    private val networkExecutor: Executor
) : PageKeyRepository<T> {

    protected abstract val sourceFactory: BaseDataSourceFactory<T>

    @MainThread
    override fun getItems(): Listing<T> {

        val livePagedList = LivePagedListBuilder(sourceFactory, PAGE_SIZE)
            // provide custom executor for network requests, otherwise it will default to
            // Arch Components' IO pool which is also used for disk access
            .setFetchExecutor(networkExecutor)
            .build()

        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.initialLoad
        }

        val networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.networkState
        }

        return Listing(
            pagedList = livePagedList,
            networkState = networkState,
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            },
            refreshState = refreshState,
            retry = {
                sourceFactory.sourceLiveData.value?.retryAllFailed()
            }
        )
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}
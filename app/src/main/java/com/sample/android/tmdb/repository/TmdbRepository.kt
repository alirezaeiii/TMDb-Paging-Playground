package com.sample.android.tmdb.repository

import android.content.Context
import com.sample.android.tmdb.R
import com.sample.android.tmdb.domain.ItemWrapper
import com.sample.android.tmdb.domain.Items
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.util.ViewState
import com.sample.android.tmdb.util.isNetworkAvailable
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow

abstract class TmdbRepository<T : TmdbItem>(
    private val context: Context
) {

    protected abstract suspend fun popular(): ItemWrapper<T>

    protected abstract suspend fun latest(): ItemWrapper<T>

    protected abstract suspend fun topRated(): ItemWrapper<T>

    val result = flow {
        emit(ViewState.Loading)
        if (!context.isNetworkAvailable()) {
            emit(ViewState.Error(context.getString(R.string.failed_network_msg)))
            return@flow
        }
        try {
            coroutineScope {
                val popularItems = async { popular() }
                val latestItems = async { latest() }
                val topRatedItems = async { topRated() }

                emit(
                    ViewState.Success(
                        Items(
                            popularItems.await().items,
                            latestItems.await().items,
                            topRatedItems.await().items
                        )
                    )
                )
            }
        } catch (t: Throwable) {
            emit(ViewState.Error(context.getString(R.string.failed_loading_msg)))
        }
    }
}
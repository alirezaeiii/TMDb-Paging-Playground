package com.sample.android.tmdb.domain

import android.content.Context
import com.sample.android.tmdb.R
import com.sample.android.tmdb.data.FeedWrapper
import com.sample.android.tmdb.data.ItemWrapper
import com.sample.android.tmdb.data.TmdbItem
import com.sample.android.tmdb.ui.paging.main.SortType
import com.sample.android.tmdb.util.ViewState
import com.sample.android.tmdb.util.isNetworkAvailable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseFeedRepository<T : TmdbItem>(
    private val context: Context,
    ioDispatcher: CoroutineDispatcher
) {

    protected abstract suspend fun popularItems(): ItemWrapper<T>

    protected abstract suspend fun nowPlayingItems(): ItemWrapper<T>

    protected abstract suspend fun latestItems(): ItemWrapper<T>

    protected abstract suspend fun topRatedItems(): ItemWrapper<T>

    protected abstract suspend fun trendingItems(): ItemWrapper<T>

    protected abstract fun getNowPlayingResId(): Int

    protected abstract fun getLatestResId(): Int

    val result = flow {
        emit(ViewState.Loading)
        if (context.isNetworkAvailable()) {
            try {
                coroutineScope {
                    val trendingDeferred: Deferred<ItemWrapper<T>> = async { trendingItems() }
                    val popularDeferred: Deferred<ItemWrapper<T>> = async { popularItems() }
                    val nowPlayingDeferred: Deferred<ItemWrapper<T>> = async { nowPlayingItems() }
                    val latestDeferred: Deferred<ItemWrapper<T>> = async { latestItems() }
                    val topRatedDeferred: Deferred<ItemWrapper<T>> = async { topRatedItems() }

                    emit(
                        ViewState.Success(
                            listOf(
                                FeedWrapper(
                                    trendingDeferred.await().items,
                                    R.string.text_trending,
                                    SortType.TRENDING
                                ),
                                FeedWrapper(
                                    popularDeferred.await().items,
                                    R.string.text_popular,
                                    SortType.MOST_POPULAR
                                ),
                                FeedWrapper(
                                    nowPlayingDeferred.await().items,
                                    getNowPlayingResId(),
                                    SortType.NOW_PLAYING
                                ),
                                FeedWrapper(
                                    latestDeferred.await().items,
                                    getLatestResId(),
                                    SortType.UPCOMING
                                ),
                                FeedWrapper(
                                    topRatedDeferred.await().items,
                                    R.string.text_highest_rate,
                                    SortType.HIGHEST_RATED
                                )
                            )
                        )
                    )
                }
            } catch (t: Throwable) {
                emit(ViewState.Error(context.getString(R.string.failed_loading_msg)))
            }
        } else {
            emit(ViewState.Error(context.getString(R.string.failed_network_msg)))
        }
    }.flowOn(ioDispatcher)
}
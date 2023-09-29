package com.sample.android.tmdb.domain.repository

import android.content.Context
import com.sample.android.tmdb.R
import com.sample.android.tmdb.domain.model.FeedWrapper
import com.sample.android.tmdb.domain.model.TmdbItem
import com.sample.android.tmdb.domain.model.SortType
import com.sample.android.tmdb.util.Resource
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

    protected abstract suspend fun popularItems(): List<T>

    protected abstract suspend fun nowPlayingItems(): List<T>

    protected abstract suspend fun latestItems(): List<T>

    protected abstract suspend fun topRatedItems(): List<T>

    protected abstract suspend fun trendingItems(): List<T>

    protected abstract suspend fun discoverItems(): List<T>

    protected abstract fun getNowPlayingResId(): Int

    protected abstract fun getLatestResId(): Int

    val result = flow {
        emit(Resource.Loading)
        if (context.isNetworkAvailable()) {
            try {
                coroutineScope {
                    val trendingDeferred: Deferred<List<T>> = async { trendingItems() }
                    val popularDeferred: Deferred<List<T>> = async { popularItems() }
                    val nowPlayingDeferred: Deferred<List<T>> = async { nowPlayingItems() }
                    val latestDeferred: Deferred<List<T>> = async { latestItems() }
                    val topRatedDeferred: Deferred<List<T>> = async { topRatedItems() }
                    val discoverDeferred: Deferred<List<T>> = async { discoverItems() }

                    emit(
                        Resource.Success(
                            listOf(
                                FeedWrapper(
                                    trendingDeferred.await(),
                                    R.string.text_trending,
                                    SortType.TRENDING
                                ),
                                FeedWrapper(
                                    popularDeferred.await(),
                                    R.string.text_popular,
                                    SortType.MOST_POPULAR
                                ),
                                FeedWrapper(
                                    nowPlayingDeferred.await(),
                                    getNowPlayingResId(),
                                    SortType.NOW_PLAYING
                                ),
                                FeedWrapper(
                                    discoverDeferred.await(),
                                    R.string.text_discover,
                                    SortType.DISCOVER
                                ),
                                FeedWrapper(
                                    latestDeferred.await(),
                                    getLatestResId(),
                                    SortType.UPCOMING
                                ),
                                FeedWrapper(
                                    topRatedDeferred.await(),
                                    R.string.text_highest_rate,
                                    SortType.HIGHEST_RATED
                                )
                            )
                        )
                    )
                }
            } catch (t: Throwable) {
                emit(Resource.Error(context.getString(R.string.failed_loading_msg)))
            }
        } else {
            emit(Resource.Error(context.getString(R.string.failed_network_msg)))
        }
    }.flowOn(ioDispatcher)
}
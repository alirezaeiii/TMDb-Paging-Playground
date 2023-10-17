package com.sample.android.tmdb.domain.repository

import android.content.Context
import com.sample.android.tmdb.R
import com.sample.android.tmdb.domain.model.FeedWrapper
import com.sample.android.tmdb.domain.model.SortType
import com.sample.android.tmdb.domain.model.TmdbItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

abstract class BaseFeedRepository<T : TmdbItem>(
    context: Context,
    ioDispatcher: CoroutineDispatcher
) : BaseRepository<List<FeedWrapper<T>>>(context, ioDispatcher) {

    protected abstract suspend fun popularItems(): List<T>

    protected abstract suspend fun nowPlayingItems(): List<T>

    protected abstract suspend fun latestItems(): List<T>

    protected abstract suspend fun topRatedItems(): List<T>

    protected abstract suspend fun trendingItems(): List<T>

    protected abstract suspend fun discoverItems(): List<T>

    protected abstract fun getNowPlayingResId(): Int

    protected abstract fun getLatestResId(): Int

    override suspend fun getSuccessResult(): List<FeedWrapper<T>> {
        val trendingDeferred: Deferred<List<T>>
        val nowPlayingDeferred: Deferred<List<T>>
        val popularDeferred: Deferred<List<T>>
        val latestDeferred: Deferred<List<T>>
        val topRatedDeferred: Deferred<List<T>>
        val discoverDeferred: Deferred<List<T>>
        coroutineScope {
            trendingDeferred = async { trendingItems() }
            nowPlayingDeferred = async { nowPlayingItems() }
            popularDeferred = async { popularItems() }
            latestDeferred = async { latestItems() }
            topRatedDeferred = async { topRatedItems() }
            discoverDeferred = async { discoverItems() }
        }
        return listOf(
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
    }
}
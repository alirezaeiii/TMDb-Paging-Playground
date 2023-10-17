package com.sample.android.tmdb.domain.repository

import android.content.Context
import com.sample.android.tmdb.R
import com.sample.android.tmdb.util.Resource
import com.sample.android.tmdb.util.isNetworkAvailable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository<T>(
    private val context: Context,
    ioDispatcher: CoroutineDispatcher
) {
    protected abstract suspend fun getSuccessResult(): T

    val result: Flow<Resource<T>> = flow {
        emit(Resource.Loading)
        if (context.isNetworkAvailable()) {
            try {
                emit(Resource.Success(getSuccessResult()))
            } catch (t: Throwable) {
                emit(Resource.Error(context.getString(R.string.failed_loading_msg)))
            }
        } else {
            emit(Resource.Error(context.getString(R.string.failed_network_msg)))
        }
    }.flowOn(ioDispatcher)
}
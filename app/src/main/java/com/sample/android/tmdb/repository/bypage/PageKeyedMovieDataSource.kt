package com.sample.android.tmdb.repository.bypage

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.api.MovieApi
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.repository.NetworkState
import com.sample.android.tmdb.vo.Movie
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class PageKeyedMovieDataSource(
        private val dataSource: MoviesRemoteDataSource,
        private val sortType: SortType?,
        private val query: String) : PageKeyedDataSource<Int, Movie>() {

    /**
     * There is no sync on the state because paging will always call loadInitial first then wait
     * for it to return some success value before calling loadAfter.
     */
    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, Movie>) {
        // ignored, since we only ever append to our initial load
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

        networkState.postValue(NetworkState.LOADING)
        fetchMovies(params.key).enqueue(
                object : retrofit2.Callback<MovieApi.MovieWrapper> {
                    override fun onFailure(call: Call<MovieApi.MovieWrapper>, t: Throwable) {
                        networkState.postValue(NetworkState.error(t.message ?: "unknown err"))
                    }

                    override fun onResponse(
                            call: Call<MovieApi.MovieWrapper>,
                            response: Response<MovieApi.MovieWrapper>) {
                        if (response.isSuccessful) {
                            val data = response.body()?.movies
                            val items = data?.map { it } ?: emptyList()
                            callback.onResult(items, params.key + 1)
                            networkState.postValue(NetworkState.LOADED)
                        } else {
                            networkState.postValue(
                                    NetworkState.error("error code: ${response.code()}"))
                        }
                    }
                }
        )
    }

    override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, Movie>) {
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        // triggered by a refresh, we better execute sync
        try {
            val response = fetchMovies(1).execute()
            if (response.isSuccessful) {
                val data = response.body()?.movies
                val items = data?.map { it } ?: emptyList()
                networkState.postValue(NetworkState.LOADED)
                initialLoad.postValue(NetworkState.LOADED)
                callback.onResult(items, null, 2)
            } else {
                initNetworkError("error code: ${response.code()}")
            }
        } catch (ioException: IOException) {
            initNetworkError(ioException.message ?: "unknown error")
        }
    }

    private fun initNetworkError(msg: String) {
        val error = NetworkState.error(msg)
        networkState.postValue(error)
        initialLoad.postValue(error)
    }

    private fun fetchMovies(page : Int): Call<MovieApi.MovieWrapper> {
        if (sortType != null) {
            return dataSource.fetchMovies(sortType = sortType, page = page)
        } else if (query.isNotEmpty()) {
            return dataSource.fetchMovies(page = page, query = query)
        }
        throw RuntimeException("Unknown state to fetch movies")
    }
}
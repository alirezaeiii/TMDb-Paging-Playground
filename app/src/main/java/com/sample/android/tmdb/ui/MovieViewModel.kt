package com.sample.android.tmdb.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations.map
import android.arch.lifecycle.Transformations.switchMap
import android.arch.lifecycle.ViewModel
import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.repository.bypage.PageKeyRepository
import java.util.concurrent.Executors

class MovieViewModel(
        dataSource: MoviesRemoteDataSource,
        sortType: SortType?) : ViewModel() {

    constructor(dataSource: MoviesRemoteDataSource) : this(
            dataSource = dataSource,
            sortType = null)

    // thread pool used for network requests
    @Suppress("PrivatePropertyName")
    private val NETWORK_IO = Executors.newFixedThreadPool(5)

    private val query = MutableLiveData<String>()
    private val repoResult = map(query) {
        PageKeyRepository(
                dataSource = dataSource,
                sortType = sortType,
                networkExecutor = NETWORK_IO).getMovies(it, 20)
    }

    val movies = switchMap(repoResult) { it.pagedList }!!
    val networkState = switchMap(repoResult) { it.networkState }!!
    val refreshState = switchMap(repoResult) { it.refreshState }!!

    fun refresh() {
        repoResult.value?.refresh?.invoke()
    }

    fun showQuery(query: String?): Boolean {
        if (this.query.value == query) {
            return false
        }
        this.query.value = query
        return true
    }
}
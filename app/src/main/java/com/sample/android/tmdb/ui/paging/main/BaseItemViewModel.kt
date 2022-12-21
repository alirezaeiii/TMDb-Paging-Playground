package com.sample.android.tmdb.ui.paging.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.sample.android.tmdb.ui.paging.BaseViewModel
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.paging.Listing
import com.sample.android.tmdb.paging.BasePageKeyRepository

abstract class BaseItemViewModel<T : TmdbItem>(app: Application) : BaseViewModel<T>(app) {

    protected abstract val mainRepoResult : BasePageKeyRepository<T>

    override val repoResult: LiveData<Listing<T>> = liveData {
        emit(mainRepoResult.getItems())
    }
}
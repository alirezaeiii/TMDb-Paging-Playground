package com.sample.android.tmdb.ui.paging.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.sample.android.tmdb.ui.paging.BasePagingViewModel
import com.sample.android.tmdb.domain.model.TmdbItem
import com.sample.android.tmdb.domain.paging.Listing
import com.sample.android.tmdb.domain.paging.BasePageKeyRepository

abstract class BaseMainPagingViewModel<T : TmdbItem>(app: Application) : BasePagingViewModel<T>(app) {

    protected abstract val mainRepoResult : BasePageKeyRepository<T>

    override val repoResult: LiveData<Listing<T>> = liveData {
        emit(mainRepoResult.getItems())
    }
}
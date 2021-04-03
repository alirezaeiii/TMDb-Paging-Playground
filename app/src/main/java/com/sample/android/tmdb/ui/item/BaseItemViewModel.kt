package com.sample.android.tmdb.ui.item

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.paging.Listing
import com.sample.android.tmdb.paging.BasePageKeyRepository
import com.sample.android.tmdb.ui.TmdbViewModel

abstract class BaseItemViewModel<T : TmdbItem>(app: Application) : TmdbViewModel<T>(app = app) {

    protected abstract val baseRepoResult : BasePageKeyRepository<T>

    override val repoResult: LiveData<Listing<T>> = liveData {
        emit(baseRepoResult.getItems())
    }
}
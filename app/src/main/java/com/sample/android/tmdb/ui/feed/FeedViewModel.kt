package com.sample.android.tmdb.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.android.tmdb.data.FeedWrapper
import com.sample.android.tmdb.data.TmdbItem
import com.sample.android.tmdb.domain.BaseFeedRepository
import com.sample.android.tmdb.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class FeedViewModel<T : TmdbItem>(
    private val repository: BaseFeedRepository<T>
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<Resource<List<FeedWrapper<T>>>>(Resource.Loading)
    val stateFlow: StateFlow<Resource<List<FeedWrapper<T>>>>
        get() = _stateFlow.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            repository.result.collect {
                _stateFlow.tryEmit(it)
            }
        }
    }
}
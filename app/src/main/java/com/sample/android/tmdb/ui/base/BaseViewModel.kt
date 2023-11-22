package com.sample.android.tmdb.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.android.tmdb.domain.repository.BaseRepository
import com.sample.android.tmdb.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class BaseViewModel<T>(
    private val repository: BaseRepository<T>
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<Resource<T>>(Resource.Loading)
    val stateFlow: StateFlow<Resource<T>>
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
package com.sample.android.tmdb.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.sample.android.tmdb.ui.base.BaseViewModel
import com.sample.android.tmdb.util.Resource

@Composable
fun <T> Content(
    viewModel: BaseViewModel<T>,
    successScreen: @Composable (T) -> Unit
) {
    when (val resource = viewModel.stateFlow.collectAsState().value) {
        is Resource.Loading -> ProgressScreen()
        is Resource.Success -> successScreen(resource.data)
        is Resource.Error -> ErrorScreen(
            message = resource.message, refresh = viewModel::refresh
        )
    }
}
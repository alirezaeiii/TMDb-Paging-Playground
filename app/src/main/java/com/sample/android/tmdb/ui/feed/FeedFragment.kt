package com.sample.android.tmdb.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.sample.android.tmdb.data.FeedWrapper
import com.sample.android.tmdb.data.TmdbItem
import com.sample.android.tmdb.ui.BaseNavTypeFragment
import com.sample.android.tmdb.ui.common.ErrorScreen
import com.sample.android.tmdb.ui.common.ProgressScreen
import com.sample.android.tmdb.ui.common.TmdbTheme
import com.sample.android.tmdb.ui.common.composeView
import com.sample.android.tmdb.util.ViewState

abstract class FeedFragment<T : TmdbItem> : BaseNavTypeFragment() {

    protected abstract val viewModel: FeedViewModel<T>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = composeView {
        TmdbTheme {
            val viewState = viewModel.stateFlow.collectAsState().value
            Content(viewState = viewState)
        }
    }

    @Composable
    private fun Content(viewState: ViewState<List<FeedWrapper<T>>>) {
        when (viewState) {
            is ViewState.Loading -> ProgressScreen()
            is ViewState.Success -> {
                FeedCollectionList(
                    navType,
                    viewState.data
                ) { tmdbItem ->
                    startDetailActivity(tmdbItem)
                }
            }
            is ViewState.Error ->
                ErrorScreen(message = viewState.message, viewModel::refresh)
        }
    }
}
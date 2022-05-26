package com.sample.android.tmdb.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.sample.android.tmdb.domain.FeedWrapper
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.BaseNavTypeFragment
import com.sample.android.tmdb.ui.common.ErrorScreen
import com.sample.android.tmdb.ui.common.ProgressScreen
import com.sample.android.tmdb.ui.common.TmdbTheme
import com.sample.android.tmdb.ui.common.composeView
import com.sample.android.tmdb.util.ViewState
import com.sample.android.tmdb.util.startDetailActivity

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
                    context?.startDetailActivity(tmdbItem, navType)
                }
            }
            is ViewState.Error ->
                ErrorScreen(message = viewState.message, viewModel::refresh)
        }
    }
}
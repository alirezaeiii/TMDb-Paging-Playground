package com.sample.android.tmdb.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.sample.android.tmdb.domain.model.FeedWrapper
import com.sample.android.tmdb.domain.model.TmdbItem
import com.sample.android.tmdb.ui.BaseNavTypeFragment
import com.sample.android.tmdb.ui.common.ErrorScreen
import com.sample.android.tmdb.ui.common.ProgressScreen
import com.sample.android.tmdb.ui.common.TmdbTheme
import com.sample.android.tmdb.ui.common.composeView
import com.sample.android.tmdb.util.Resource

abstract class FeedFragment<T : TmdbItem> : BaseNavTypeFragment() {

    protected abstract val viewModel: FeedViewModel<T>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = composeView {
        TmdbTheme {
            val resource = viewModel.stateFlow.collectAsState().value
            Content(resource = resource)
        }
    }

    @Composable
    private fun Content(resource: Resource<List<FeedWrapper<T>>>) {
        when (resource) {
            is Resource.Loading -> ProgressScreen()
            is Resource.Success -> {
                FeedCollectionList(
                    navType,
                    resource.data
                ) { tmdbItem ->
                    startDetailActivity(tmdbItem)
                }
            }
            is Resource.Error ->
                ErrorScreen(message = resource.message, viewModel::refresh)
        }
    }
}
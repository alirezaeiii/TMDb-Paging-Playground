package com.sample.android.tmdb.ui.feed

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.TmdbTheme
import com.sample.android.tmdb.ui.common.ErrorView
import com.sample.android.tmdb.ui.common.composeView
import com.sample.android.tmdb.ui.detail.DetailActivity
import com.sample.android.tmdb.util.Constants.EXTRA_NAV_TYPE
import com.sample.android.tmdb.util.Constants.EXTRA_TMDB_ITEM
import com.sample.android.tmdb.util.ViewState
import dagger.android.support.DaggerFragment

abstract class FeedFragment<T : TmdbItem> : DaggerFragment() {

    protected abstract val viewModel: FeedViewModel<T>

    protected abstract val navType: NavType

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
    private fun Content(viewState: ViewState<List<List<T>>>) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            when (viewState) {
                is ViewState.Loading -> CircularProgressIndicator()
                is ViewState.Success -> {
                    FeedCollectionList(
                        requireContext(),
                        navType,
                        viewState.data,
                        OnFeedClickListener { tmdbItem ->
                            val intent = Intent(context, DetailActivity::class.java).apply {
                                putExtras(Bundle().apply {
                                    putParcelable(EXTRA_TMDB_ITEM, tmdbItem)
                                    putParcelable(EXTRA_NAV_TYPE, navType)
                                })
                            }
                            startActivity(intent)
                        })
                }
                is ViewState.Error ->
                    ErrorView(message = viewState.message, viewModel::refresh)
            }
        }
    }
}
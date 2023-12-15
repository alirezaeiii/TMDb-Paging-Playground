package com.sample.android.tmdb.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import com.sample.android.tmdb.domain.model.FeedWrapper
import com.sample.android.tmdb.domain.model.TmdbItem
import com.sample.android.tmdb.ui.base.BaseNavigationFragment
import com.sample.android.tmdb.ui.common.Content
import com.sample.android.tmdb.ui.common.TmdbTheme
import com.sample.android.tmdb.ui.common.composeView

abstract class FeedFragment<T : TmdbItem> : BaseNavigationFragment<Nothing>() {

    private var feeds: List<FeedWrapper<T>>? = null

    protected abstract val viewModel: FeedViewModel<T>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return composeView {
            TmdbTheme {
                if (feeds == null) {
                    Content(viewModel) {
                        feeds = it
                        FeedCollectionScreen()
                    }
                } else {
                    FeedCollectionScreen()
                }
            }
        }
    }

    @Composable
    private fun FeedCollectionScreen() {
        FeedCollectionList(
            navType,
            feeds!!
        ) { tmdbItem ->
            startDetailActivity(tmdbItem)
        }
    }
}
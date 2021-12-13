package com.sample.android.tmdb.ui.feed

import androidx.fragment.app.viewModels
import com.sample.android.tmdb.domain.TVShow

class FeedTVShowFragment: FeedFragment<TVShow>() {

    override val viewModel: FeedTVShowViewModel by viewModels()
}
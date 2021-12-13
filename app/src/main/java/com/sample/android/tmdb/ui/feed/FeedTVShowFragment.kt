package com.sample.android.tmdb.ui.feed

import androidx.fragment.app.viewModels
import com.sample.android.tmdb.domain.TVShow
import javax.inject.Inject

class FeedTVShowFragment @Inject
constructor() // Required empty public constructor
    : FeedFragment<TVShow>() {

    override val viewModel: FeedTVShowViewModel by viewModels()
}
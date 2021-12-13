package com.sample.android.tmdb.ui.feed

import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.repository.TVShowRepository

class FeedTVShowViewModel(repository: TVShowRepository) :
    FeedViewModel<TVShow>(repository)
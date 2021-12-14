package com.sample.android.tmdb.ui.feed

import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.repository.TmdbRepository

class FeedTVShowViewModel(repository: TmdbRepository<TVShow>) :
    FeedViewModel<TVShow>(repository)
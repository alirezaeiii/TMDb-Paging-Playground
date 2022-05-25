package com.sample.android.tmdb.ui.paging.search.movie

import com.sample.android.tmdb.R
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.ui.paging.search.BaseSearchFragment
import com.sample.android.tmdb.ui.paging.search.SearchActivity
import javax.inject.Inject

class SearchMovieActivity: SearchActivity<Movie>() {

    @Inject
    lateinit var searchMovieFragment: SearchMovieFragment

    override val fragment: BaseSearchFragment<Movie>
        get() = searchMovieFragment

    override val hintId: Int
        get() = R.string.search_hint_movies


}
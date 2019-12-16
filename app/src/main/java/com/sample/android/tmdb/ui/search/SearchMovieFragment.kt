package com.sample.android.tmdb.ui.search

import com.sample.android.tmdb.ui.movie.MovieAdapter
import com.sample.android.tmdb.ui.MovieFragment
import com.sample.android.tmdb.util.NavType
import com.sample.android.tmdb.util.SortType
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class SearchMovieFragment @Inject
constructor() // Required empty public constructor
    : MovieFragment() {

    override val sortType: SortType? = null

    override fun getNavType(): NavType = (activity as SearchActivity).navType

    fun search(query: String?) {
        if (viewModel.showQuery(query)) {
            list.scrollToPosition(0)
            (list.adapter as MovieAdapter).submitList(null)
        }
    }
}
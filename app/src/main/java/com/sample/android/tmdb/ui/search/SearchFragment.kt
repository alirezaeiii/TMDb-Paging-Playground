package com.sample.android.tmdb.ui.search

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.ui.movie.MovieAdapter
import com.sample.android.tmdb.ui.movie.MovieBaseFragment
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

@ActivityScoped
class SearchMovieFragment @Inject
constructor() // Required empty public constructor
    : MovieBaseFragment() {

    override fun getSortType(): SortType? = null

    fun searchViewClicked(query: String?) {
        if (model.showQuery(query)) {
            list.scrollToPosition(0)
            (list.adapter as? MovieAdapter)?.submitList(null)
        }
    }

    override fun shouldIncrementEspressoIdlingResource() =
            (activity as SearchActivity).search_view.query.isNotEmpty()
}
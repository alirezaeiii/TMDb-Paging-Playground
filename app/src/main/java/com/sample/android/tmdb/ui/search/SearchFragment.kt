package com.sample.android.tmdb.ui.search

import android.support.v7.app.ActionBar
import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.ui.MainFragment
import com.sample.android.tmdb.ui.MovieAdapter
import com.sample.android.tmdb.ui.MovieViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

@ActivityScoped
class SearchFragment @Inject
constructor() // Required empty public constructor
    : MainFragment() {

    override fun initViewModel() {
        model = getViewModel()
    }

    override fun getMoviesViewModel() =
            MovieViewModel(dataSource = dataSource)

    fun searchViewClicked(query: String?) {
        if (model.showQuery(query)) {
            list.scrollToPosition(0)
            (list.adapter as? MovieAdapter)?.submitList(null)
        }
    }

    override fun setupToolbar(ab: ActionBar?) {
        ab?.setDisplayHomeAsUpEnabled(true)
        ab?.setDisplayShowHomeEnabled(true)
    }
}
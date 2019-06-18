package com.sample.android.tmdb.ui.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.ui.*
import com.sample.android.tmdb.vo.Movie
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

@ActivityScoped
class SearchFragment @Inject
constructor() // Required empty public constructor
    : BaseFragment<Movie>() {
    override fun getAdapter(): ItemAdapter<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSortType(): SortType? = null

    override fun initViewModel() {
        model = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TVShowsViewModel(dataSource = dataSource,
                        sortType = getSortType()) as T
            }
        })[MovieViewModel::class.java]
    }

    fun searchViewClicked(query: String?) {
        if (model.showQuery(query)) {
            list.scrollToPosition(0)
            (list.adapter as? MovieAdapter)?.submitList(null)
        }
    }

    override fun shouldIncrementEspressoIdlingResource() =
            (activity as SearchActivity).search_view.query.isNotEmpty()
}
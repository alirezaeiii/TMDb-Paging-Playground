package com.sample.android.tmdb.ui.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.ui.ItemAdapter
import com.sample.android.tmdb.ui.tvshow.TVShowAdapter
import com.sample.android.tmdb.ui.tvshow.TVShowsViewModel
import com.sample.android.tmdb.vo.TVShow
import javax.inject.Inject

@ActivityScoped
class SearchTVShowFragment @Inject
constructor() // Required empty public constructor
    : SearchBaseFragment<TVShow>() {

    override fun initViewModel() {
        model = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TVShowsViewModel(dataSource = dataSource,
                        sortType = getSortType()) as T
            }
        })[TVShowsViewModel::class.java]
    }

    override fun getAdapter(): ItemAdapter<TVShow> = TVShowAdapter()
}
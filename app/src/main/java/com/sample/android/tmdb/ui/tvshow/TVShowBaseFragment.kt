package com.sample.android.tmdb.ui.tvshow

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.sample.android.tmdb.ui.BaseFragment
import com.sample.android.tmdb.ui.ItemAdapter
import com.sample.android.tmdb.vo.TVShow

abstract class TVShowBaseFragment : BaseFragment<TVShow, TVShow>() {

    override fun initViewModel() {
        model = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TVShowsViewModel(dataSource = dataSource,
                        sortType = getSortType()) as T
            }
        })[TVShowsViewModel::class.java]

        model.showQuery("")
    }

    override fun getAdapter(): ItemAdapter<TVShow> = TVShowAdapter(this)
}
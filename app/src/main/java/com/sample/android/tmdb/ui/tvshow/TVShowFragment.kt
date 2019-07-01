package com.sample.android.tmdb.ui.tvshow

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.sample.android.tmdb.NavType
import com.sample.android.tmdb.ui.BaseFragment
import com.sample.android.tmdb.ui.ItemAdapter
import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.ui.detail.DetailActivity
import com.sample.android.tmdb.vo.TVShow

abstract class TVShowFragment : BaseFragment<TVShow, TVShow>() {

    override fun initViewModel() {
        model = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TVShowsViewModel(dataSource = dataSource,
                        sortType = getSortType()) as T
            }
        })[TVShowsViewModel::class.java]
    }

    override fun getAdapter(): ItemAdapter<TVShow> = TVShowAdapter(this)

    override fun putItemParcelable(bundle: Bundle, e: TVShow) {
        bundle.putParcelable(DetailActivity.EXTRA_TV_SHOW, e)
    }

    override fun getNavType(): NavType = (activity as MainActivity).viewModel.currentType.value!!
}
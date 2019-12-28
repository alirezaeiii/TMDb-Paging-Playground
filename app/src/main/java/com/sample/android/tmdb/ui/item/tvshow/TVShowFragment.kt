package com.sample.android.tmdb.ui.item.tvshow

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.ui.item.BaseItemFragment
import com.sample.android.tmdb.ui.ItemAdapter
import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.ui.detail.EXTRA_TV_SHOW
import com.sample.android.tmdb.util.NavType

abstract class TVShowFragment : BaseItemFragment<TVShow>() {

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TVShowsViewModel(dataSource = dataSource,
                        sortType = sortType) as T
            }
        })[TVShowsViewModel::class.java]
    }

    override fun getAdapter(retryCallback: () -> Unit): ItemAdapter<TVShow> = TVShowAdapter(this, retryCallback)

    override val keyParcelable = EXTRA_TV_SHOW

    override fun getNavType(): NavType? = (activity as MainActivity).getNavType()
}
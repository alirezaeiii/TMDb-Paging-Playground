package com.sample.android.tmdb.ui.detail.tvshow

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.view.View
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.FragmentDetailTvShowBinding
import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.ui.detail.DetailFragment
import com.sample.android.tmdb.domain.TVShow
import org.jetbrains.annotations.Nullable
import javax.inject.Inject

@ActivityScoped
class TVShowDetailFragment @Inject
constructor() // Required empty public constructor
    : DetailFragment<TVShow>() {

    @Inject
    @Nullable
    lateinit var tvShow: TVShow

    override fun getViewModel() =
            ViewModelProviders.of(requireActivity(), object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return TVShowDetailViewModel(dataSource, tvShow) as T
                }
            })[TVShowDetailViewModel::class.java]

    override fun getLayoutId(): Int = R.layout.fragment_detail_tv_show

    override fun initViewBinding(root: View): FragmentDetailTvShowBinding =
            FragmentDetailTvShowBinding.bind(root).apply {
                tvShow = this@TVShowDetailFragment.tvShow
            }

    override fun getTmdbItem(): TVShow = tvShow
}
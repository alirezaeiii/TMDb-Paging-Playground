package com.sample.android.tmdb.ui.detail.tvshow

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.view.View
import com.sample.android.tmdb.BR
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.FragmentDetailTvShowBinding
import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.ui.detail.DetailFragment
import com.sample.android.tmdb.vo.TVShow
import com.sample.android.tmdb.vo.TmdbItem
import org.jetbrains.annotations.Nullable
import javax.inject.Inject

@ActivityScoped
class TVShowDetailFragment @Inject
constructor() // Required empty public constructor
    : DetailFragment<TVShow>() {

    @Inject @Nullable
    lateinit var tvShow : TVShow

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TVShowDetailViewModel(dataSource) as T
            }
        })[TVShowDetailViewModel::class.java]
    }

    override fun getLayoutId(): Int = R.layout.fragment_detail_tv_show

    override fun initViewBinding(root: View) {
        FragmentDetailTvShowBinding.bind(root).apply {
            tvShow = this@TVShowDetailFragment.tvShow
            setVariable(BR.vm, viewModel)
            lifecycleOwner = this@TVShowDetailFragment
        }
        viewModel.showTrailers(tvShow).let { compositeDisposable.add(it) }
        viewModel.showCast(tvShow).let { compositeDisposable.add(it) }
    }

    override fun getTmdbItem(): TmdbItem = tvShow
}
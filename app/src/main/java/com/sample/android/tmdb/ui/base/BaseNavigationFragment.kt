package com.sample.android.tmdb.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.sample.android.tmdb.domain.model.TmdbItem
import com.sample.android.tmdb.ui.detail.movie.DetailMovieActivity
import com.sample.android.tmdb.ui.detail.tvshow.DetailTVShowActivity
import com.sample.android.tmdb.ui.feed.NavType
import com.sample.android.tmdb.util.Constants

abstract class BaseNavigationFragment<VB: ViewDataBinding> : BaseFragment<VB>()  {

    protected abstract val navType: NavType

    protected fun startDetailActivity(tmdbItem: TmdbItem) {
        val activityClass = when (navType) {
            NavType.MOVIES -> DetailMovieActivity::class.java
            NavType.TV_SERIES -> DetailTVShowActivity::class.java
            else -> throw RuntimeException("Unknown item to start detail Activity")
        }
        val intent = Intent(requireActivity(), activityClass).apply {
            putExtras(Bundle().apply {
                putParcelable(Constants.EXTRA_TMDB_ITEM, tmdbItem)
            })
        }
        startActivity(intent)
    }
}
package com.sample.android.tmdb.ui.tvshow

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v4.view.ViewCompat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.sample.android.tmdb.ui.BaseFragment
import com.sample.android.tmdb.ui.ItemAdapter
import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.ui.detail.DetailActivity
import com.sample.android.tmdb.ui.detail.DetailActivity.Companion.EXTRA_NAV_TYPE
import com.sample.android.tmdb.ui.detail.DetailActivity.Companion.EXTRA_TV_SHOW
import com.sample.android.tmdb.vo.TVShow

abstract class TVShowBaseFragment : BaseFragment<TVShow>(), TVShowClickCallback {

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

    override fun onClick(tvShow: TVShow, poster: ImageView, name: TextView) {
        val intent = Intent(activity, DetailActivity::class.java).apply {
            putExtras(Bundle().apply {
                putParcelable(EXTRA_TV_SHOW, tvShow)
                putParcelable(EXTRA_NAV_TYPE, (activity as MainActivity).viewModel.currentType.value)
            })
        }
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(),

                // Now we provide a list of Pair items which contain the view we can transitioning
                // from, and the name of the view it is transitioning to, in the launched activity
                Pair<View, String>(poster, ViewCompat.getTransitionName(poster)),
                Pair<View, String>(name, ViewCompat.getTransitionName(name)))

        // Now we can start the Activity, providing the activity options as a bundle
        ActivityCompat.startActivity(requireContext(), intent, activityOptions.toBundle())
    }
}
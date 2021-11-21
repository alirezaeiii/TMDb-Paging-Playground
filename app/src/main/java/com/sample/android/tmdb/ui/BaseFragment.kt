package com.sample.android.tmdb.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.sample.android.tmdb.R
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.paging.Status.RUNNING
import com.sample.android.tmdb.ui.detail.DetailActivity
import com.sample.android.tmdb.ui.detail.EXTRA_NAV_TYPE
import com.sample.android.tmdb.ui.detail.EXTRA_TMDB_ITEM
import com.sample.android.tmdb.util.Firebase
import com.sample.android.tmdb.widget.MarginDecoration
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_main.view.*
import javax.inject.Inject

abstract class BaseFragment<T : TmdbItem> : DaggerFragment(), TmdbClickCallback<T> {

    @Inject
    lateinit var firebase: Firebase

    protected abstract val viewModel: BaseViewModel<T>

    protected abstract val navType: NavType?

    protected open fun refresh() {
        viewModel.refresh()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_main, container, false)

        val tmdbAdapter = TmdbAdapter({ viewModel.retry() }, this)

        with(root) {

            swipe_refresh.apply {
                setColorSchemeColors(
                        ContextCompat.getColor(context, R.color.colorPrimary),
                        ContextCompat.getColor(context, R.color.colorAccent),
                        ContextCompat.getColor(context, R.color.colorPrimaryDark)
                )

                viewModel.refreshState.observe(viewLifecycleOwner, {
                    isRefreshing = it.status == RUNNING
                    tmdbAdapter.setRefreshState(it)
                })

                setOnRefreshListener { refresh() }
            }

            recyclerView.apply {
                addItemDecoration(MarginDecoration(context))
                setHasFixedSize(true)
                adapter = tmdbAdapter
                manager.spanSizeLookup = object : SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (tmdbAdapter.getItemViewType(position) == R.layout.tmdb_item)
                            1 else manager.spanCount
                    }
                }
            }
        }

        viewModel.items.observe(this, {
            tmdbAdapter.submitList(it)
        })

        viewModel.networkState.observe(this, {
            tmdbAdapter.setNetworkState(it)
        })

        return root
    }

    override fun onClick(t: T, poster: ImageView) {
        val intent = Intent(activity, DetailActivity::class.java).apply {
            putExtras(Bundle().apply {
                putParcelable(EXTRA_TMDB_ITEM, t)
                putParcelable(EXTRA_NAV_TYPE, navType)
            })
        }
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(),

                // Now we provide a list of Pair items which contain the view we can transitioning
                // from, and the name of the view it is transitioning to, in the launched activity
                Pair(poster, ViewCompat.getTransitionName(poster)))

        // Now we can start the Activity, providing the activity options as a bundle
        ActivityCompat.startActivity(requireContext(), intent, activityOptions.toBundle())
    }
}
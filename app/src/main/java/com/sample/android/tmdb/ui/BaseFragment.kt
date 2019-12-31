package com.sample.android.tmdb.ui

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat
import android.support.v4.util.Pair
import android.support.v4.view.ViewCompat
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sample.android.tmdb.R
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.repository.NetworkState
import com.sample.android.tmdb.ui.detail.DetailActivity
import com.sample.android.tmdb.ui.detail.EXTRA_NAV_TYPE
import com.sample.android.tmdb.util.NavType
import com.sample.android.tmdb.widget.MarginDecoration
import kotlinx.android.synthetic.main.fragment_main.view.*

abstract class BaseFragment<T : TmdbItem> : BaseDaggerFragment(), TmdbClickCallback<T> {

    protected val viewModel: TmdbViewModel<T> by lazy { initViewModel() }

    protected abstract val keyParcelable: String

    protected abstract fun initViewModel(): TmdbViewModel<T>

    protected abstract fun getAdapter(retryCallback: () -> Unit): TmdbAdapter<T>

    protected abstract fun getNavType(): NavType?

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_main, container, false)

        val itemAdapter = getAdapter {
            viewModel.retry()
        }

        with(root) {

            swipe_refresh.apply {
                setColorSchemeColors(
                        ContextCompat.getColor(context, R.color.colorPrimary),
                        ContextCompat.getColor(context, R.color.colorAccent),
                        ContextCompat.getColor(context, R.color.colorPrimaryDark)
                )

                viewModel.refreshState.observe(viewLifecycleOwner, Observer {
                    isRefreshing = it == NetworkState.LOADING
                })

                setOnRefreshListener { viewModel.refresh() }
            }

            list.apply {
                addItemDecoration(MarginDecoration(context))
                setHasFixedSize(true)
                adapter = itemAdapter
                manager.spanSizeLookup = object : SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (itemAdapter.getItemViewType(position) != R.layout.network_state_item)
                            1 else manager.spanCount
                    }
                }
            }
        }

        viewModel.items.observe(this, Observer<PagedList<T>> {
            itemAdapter.submitList(it)
        })

        viewModel.networkState.observe(this, Observer {
            itemAdapter.setNetworkState(it)
        })

        return root
    }

    override fun onClick(t: T, poster: ImageView, name: TextView) {
        val intent = Intent(activity, DetailActivity::class.java).apply {
            putExtras(Bundle().apply {
                putParcelable(keyParcelable, t)
                putParcelable(EXTRA_NAV_TYPE, getNavType())
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
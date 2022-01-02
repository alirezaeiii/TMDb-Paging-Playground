package com.sample.android.tmdb.ui.paging

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.sample.android.tmdb.R
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.paging.Status.RUNNING
import com.sample.android.tmdb.ui.detail.DetailActivity
import com.sample.android.tmdb.ui.feed.NavType
import com.sample.android.tmdb.util.Constants.EXTRA_NAV_TYPE
import com.sample.android.tmdb.util.Constants.EXTRA_TMDB_ITEM
import com.sample.android.tmdb.widget.MarginDecoration
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_main.view.*

abstract class BaseFragment<T : TmdbItem> : DaggerFragment(), TmdbClickCallback<T> {

    protected abstract val viewModel: BaseViewModel<T>

    protected abstract val navType: NavType

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

        viewModel.items.observe(viewLifecycleOwner, {
            tmdbAdapter.submitList(it)
        })

        viewModel.networkState.observe(viewLifecycleOwner, {
            tmdbAdapter.setNetworkState(it)
        })

        return root
    }

    override fun onClick(t: T, poster: View) {
        val intent = Intent(activity, DetailActivity::class.java).apply {
            putExtras(Bundle().apply {
                putParcelable(EXTRA_TMDB_ITEM, t)
                putParcelable(EXTRA_NAV_TYPE, navType)
            })
        }
        val options = ActivityOptions.makeSceneTransitionAnimation(
            activity,
            poster, t.name
        )
        startActivity(intent, options.toBundle())
    }
}
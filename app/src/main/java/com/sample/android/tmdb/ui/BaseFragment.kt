package com.sample.android.tmdb.ui

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.sample.android.tmdb.R
import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.databinding.FragmentMainBinding
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.repository.NetworkState
import com.sample.android.tmdb.repository.Status.FAILED
import com.sample.android.tmdb.util.EspressoIdlingResource
import com.sample.android.tmdb.vo.TmdbItem
import com.sample.android.tmdb.widget.MarginDecoration
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_main.view.*
import javax.inject.Inject

abstract class BaseFragment<T : TmdbItem> : DaggerFragment() {

    @Inject
    lateinit var dataSource: MoviesRemoteDataSource

    protected lateinit var model: ItemViewModel<T>

    protected abstract fun getSortType(): SortType?

    protected open fun shouldIncrementEspressoIdlingResource() = true

    protected abstract fun initViewModel()

    protected abstract fun getAdapter() : ItemAdapter<T>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        initViewModel()

        with(binding.root) {

            swipe_refresh.apply {
                setColorSchemeColors(
                        ContextCompat.getColor(context, R.color.colorPrimary),
                        ContextCompat.getColor(context, R.color.colorAccent),
                        ContextCompat.getColor(context, R.color.colorPrimaryDark)
                )

                model.refreshState.observe(this@BaseFragment, Observer {
                    isRefreshing = it == NetworkState.LOADING
                })

                setOnRefreshListener { model.refresh() }
            }

            retry_button.apply {
                setOnClickListener {
                    model.refresh()
                }
            }

            val adapter = getAdapter()

            list.apply {

                addItemDecoration(MarginDecoration(context))

                setHasFixedSize(true)

                list.adapter = adapter
            }

            if (shouldIncrementEspressoIdlingResource()) {
                // The network request might be handled in a different thread so make sure Espresso knows
                // that the app is busy until the response is handled.
                EspressoIdlingResource.increment() // App is busy until further notice
            }

            val controller = AnimationUtils
                    .loadLayoutAnimation(context,
                            R.anim.grid_layout_animation_from_bottom)

            model.items.observe(this@BaseFragment, Observer<PagedList<T>> {

                // This callback may be called twice, once for the cache and once for loading
                // the data from the server API, so we check before decrementing, otherwise
                // it throws "Counter has been corrupted!" exception.
                if (!EspressoIdlingResource.getIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement() // Set app as idle.
                }

                list.layoutAnimation = controller
                adapter.submitList(it)
                list.scheduleLayoutAnimation()
            })

            model.networkState.observe(this@BaseFragment, Observer { it ->
                adapter.setNetworkState(it)

                binding.isLoading = it?.status == FAILED

                if (it?.status == FAILED) {

                    error_msg.apply {
                        text = it.msg
                    }
                }
            })
        }
        return binding.root
    }
}
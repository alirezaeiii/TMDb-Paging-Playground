package com.sample.android.tmdb.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat
import android.support.v4.util.Pair
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sample.android.tmdb.GlideApp
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.FragmentMainBinding
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.repository.NetworkState
import com.sample.android.tmdb.repository.Status.FAILED
import com.sample.android.tmdb.ui.detail.DetailActivity
import com.sample.android.tmdb.ui.detail.DetailActivity.Companion.EXTRA_MOVIE
import com.sample.android.tmdb.ui.detail.DetailFragment.Companion.VIEW_NAME_HEADER_IMAGE
import com.sample.android.tmdb.ui.detail.DetailFragment.Companion.VIEW_NAME_HEADER_TITLE
import com.sample.android.tmdb.vo.Movie
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_main.view.*
import javax.inject.Inject

abstract class MainFragment : DaggerFragment(), MovieClickCallback {

    @Inject
    lateinit var dataSource: MoviesRemoteDataSource

    lateinit var model: MovieViewModel

    protected abstract fun getMoviesViewModel(): MovieViewModel

    protected abstract fun initViewModel()

    protected abstract fun setupToolbar(ab: ActionBar?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        initViewModel()

        with(binding.root) {

            with(activity as AppCompatActivity) {
                setSupportActionBar(toolbar)
                setupToolbar(supportActionBar)
            }

            swipe_refresh.apply {
                setColorSchemeColors(
                        ContextCompat.getColor(context, R.color.colorPrimary),
                        ContextCompat.getColor(context, R.color.colorAccent),
                        ContextCompat.getColor(context, R.color.colorPrimaryDark)
                )

                model.refreshState.observe(this@MainFragment, Observer {
                    isRefreshing = it == NetworkState.LOADING
                })

                setOnRefreshListener { model.refresh() }
            }

            retry_button.apply {
                setOnClickListener {
                    model.refresh()
                }
            }

            val glide = GlideApp.with(context)
            val adapter = MovieAdapter(glide, this@MainFragment)

            list.apply {

                layoutManager = GridLayoutManager(activity,
                        resources.getInteger(R.integer.no_of_columns),
                        GridLayoutManager.VERTICAL,
                        false)

                setHasFixedSize(true)

                list.adapter = adapter
            }

            model.movies.observe(this@MainFragment, Observer<PagedList<Movie>> {
                adapter.submitList(it)
            })

            model.networkState.observe(this@MainFragment, Observer { it ->
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

    protected fun getViewModel(): MovieViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return getMoviesViewModel() as T
            }
        })[MovieViewModel::class.java]
    }

    override fun onClick(movie: Movie, poster : ImageView, name : TextView) {
        val intent = Intent(activity, DetailActivity::class.java)
        val extras = Bundle()
        extras.putParcelable(EXTRA_MOVIE, movie)
        intent.putExtras(extras)
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(),

                // Now we provide a list of Pair items which contain the view we can transitioning
                // from, and the name of the view it is transitioning to, in the launched activity
                Pair<View, String>(poster, VIEW_NAME_HEADER_IMAGE),
                Pair<View, String>(name, VIEW_NAME_HEADER_TITLE))

        // Now we can start the Activity, providing the activity options as a bundle
        ActivityCompat.startActivity(requireContext(), intent, activityOptions.toBundle())
    }
}
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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sample.android.tmdb.R
import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.databinding.FragmentMainBinding
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.repository.NetworkState
import com.sample.android.tmdb.repository.Status.FAILED
import com.sample.android.tmdb.ui.detail.DetailActivity
import com.sample.android.tmdb.ui.detail.DetailActivity.Companion.EXTRA_MOVIE
import com.sample.android.tmdb.vo.Movie
import com.sample.android.tmdb.widget.MarginDecoration
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_main.view.*
import javax.inject.Inject

abstract class MainFragment : DaggerFragment(), MovieClickCallback {

    @Inject
    lateinit var dataSource: MoviesRemoteDataSource

    protected lateinit var model: MovieViewModel

    protected abstract fun getSortType(): SortType?

    protected open fun initViewModel() {
        model = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(dataSource = dataSource,
                        sortType = getSortType()) as T
            }
        })[MovieViewModel::class.java]
    }

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

            val adapter = MovieAdapter(this@MainFragment)

            list.apply {

                addItemDecoration(MarginDecoration(context))

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

    override fun onClick(movie: Movie, poster: ImageView, name: TextView) {
        val intent = Intent(activity, DetailActivity::class.java).apply {
            putExtras(Bundle().apply {
                putParcelable(EXTRA_MOVIE, movie)
            })
        }
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(),

                // Now we provide a list of Pair items which contain the view we can transitioning
                // from, and the name of the view it is transitioning to, in the launched activity
                Pair<View, String>(poster, getString(R.string.view_name_header_image)),
                Pair<View, String>(name, getString(R.string.view_name_header_title)))

        // Now we can start the Activity, providing the activity options as a bundle
        ActivityCompat.startActivity(requireContext(), intent, activityOptions.toBundle())
    }
}
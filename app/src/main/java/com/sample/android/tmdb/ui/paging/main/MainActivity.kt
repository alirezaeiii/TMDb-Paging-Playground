package com.sample.android.tmdb.ui.paging.main

import android.os.Bundle
import android.view.MenuItem
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.ActivityMainBinding
import com.sample.android.tmdb.ui.feed.NavType
import com.sample.android.tmdb.ui.paging.main.movie.HighRateMoviesFragment
import com.sample.android.tmdb.ui.paging.main.movie.PopularMoviesFragment
import com.sample.android.tmdb.ui.paging.main.movie.TrendingMoviesFragment
import com.sample.android.tmdb.ui.paging.main.movie.UpcomingMoviesFragment
import com.sample.android.tmdb.ui.paging.main.tvshow.HighRateTVShowFragment
import com.sample.android.tmdb.ui.paging.main.tvshow.LatestTVShowFragment
import com.sample.android.tmdb.ui.paging.main.tvshow.PopularTVShowFragment
import com.sample.android.tmdb.ui.paging.main.tvshow.TrendingTVShowFragment
import com.sample.android.tmdb.util.addFragmentToActivity
import com.sample.android.tmdb.util.setupActionBar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var popularMoviesFragment: PopularMoviesFragment

    @Inject
    lateinit var highRateMoviesFragment: HighRateMoviesFragment

    @Inject
    lateinit var upcomingMoviesFragment: UpcomingMoviesFragment

    @Inject
    lateinit var trendingMoviesFragment: TrendingMoviesFragment

    @Inject
    lateinit var popularTvShowFragment: PopularTVShowFragment

    @Inject
    lateinit var highRateTvShowFragment: HighRateTVShowFragment

    @Inject
    lateinit var latestTvShowFragment: LatestTVShowFragment

    @Inject
    lateinit var trendingTVShowFragment: TrendingTVShowFragment

    @Inject
    lateinit var navType: NavType

    @Inject
    lateinit var sortType: SortType

    private var _binding: ActivityMainBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar(binding.toolbar) {
            setDisplayHomeAsUpEnabled(true)
        }
        val fragment = when (navType) {
            NavType.MOVIES -> {
                when (sortType) {
                    SortType.MOST_POPULAR -> {
                        title = getString(R.string.popular, getString(R.string.menu_movies))
                        popularMoviesFragment
                    }
                    SortType.UPCOMING -> {
                        title = getString(R.string.upcoming, getString(R.string.menu_movies))
                        upcomingMoviesFragment
                    }
                    SortType.HIGHEST_RATED -> {
                        title = getString(R.string.highest_rate, getString(R.string.menu_movies))
                        highRateMoviesFragment
                    }
                    SortType.TRENDING -> {
                        title = getString(R.string.trending, getString(R.string.menu_movies))
                        trendingMoviesFragment
                    }
                }
            }
            NavType.TV_SERIES -> {
                when (sortType) {
                    SortType.MOST_POPULAR -> {
                        title = getString(R.string.popular, getString(R.string.menu_tv_series))
                        popularTvShowFragment
                    }
                    SortType.UPCOMING -> {
                        title = getString(R.string.upcoming, getString(R.string.menu_tv_series))
                        latestTvShowFragment
                    }
                    SortType.HIGHEST_RATED -> {
                        title = getString(R.string.highest_rate, getString(R.string.menu_tv_series))
                        highRateTvShowFragment
                    }
                    SortType.TRENDING -> {
                        title = getString(R.string.trending, getString(R.string.menu_tv_series))
                        trendingTVShowFragment
                    }
                }
            }
        }
        if (savedInstanceState == null) {
            addFragmentToActivity(fragment, R.id.fragment_container)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
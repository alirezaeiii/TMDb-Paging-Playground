package com.sample.android.tmdb.ui

import android.app.ActivityOptions
import android.content.Intent
import android.content.Intent.ACTION_SEARCH
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.sample.android.tmdb.R
import com.sample.android.tmdb.ui.item.movie.HighRateMoviesFragment
import com.sample.android.tmdb.ui.item.movie.PopularMoviesFragment
import com.sample.android.tmdb.ui.item.movie.UpcomingMoviesFragment
import com.sample.android.tmdb.ui.item.tvshow.HighRateTVShowFragment
import com.sample.android.tmdb.ui.item.tvshow.LatestTVShowFragment
import com.sample.android.tmdb.ui.item.tvshow.PopularTVShowFragment
import com.sample.android.tmdb.ui.search.EXTRA_NAV_TYPE
import com.sample.android.tmdb.ui.search.SearchActivity
import com.sample.android.tmdb.util.addFragmentToActivity
import com.sample.android.tmdb.util.replaceFragmentInActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_nav.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var popularMoviesFragment: PopularMoviesFragment

    @Inject
    lateinit var highRateMoviesFragment: HighRateMoviesFragment

    @Inject
    lateinit var upcomingMoviesFragment: UpcomingMoviesFragment

    @Inject
    lateinit var popularTVShowFragment: PopularTVShowFragment

    @Inject
    lateinit var highRateTVShowFragment: HighRateTVShowFragment

    @Inject
    lateinit var latestTVShowFragment: LatestTVShowFragment

    private lateinit var viewModel: MainViewModel

    private var movieFragment: Lazy<Fragment> = lazy { popularMoviesFragment }
    private var tvShowFragment: Lazy<Fragment> = lazy { popularTVShowFragment }
    private var movieItemId: Int = R.id.action_movie_popular
    private var tvShowItemId: Int = R.id.action_tvShow_popular

    val navType: NavType?
        get() = viewModel.currentType.value

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)

        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            // Add popular movieFragment if this is first creation
            addFragmentToActivity(movieFragment.value, R.id.fragment_container)
            nav_view.setCheckedItem(R.id.action_movies)
        }

        setupNavigationView()
        setupBottomNavigation()

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.open_nav_drawer, R.string.close_nav_drawer
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.headline.observe(this, {
            title = it
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                val searchMenuView: View = toolbar.findViewById(R.id.action_search)
                val options = ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    searchMenuView, getString(R.string.transition_search_back)
                ).toBundle()

                val intent = Intent(this, SearchActivity::class.java).apply {
                    action = ACTION_SEARCH
                    putExtras(Bundle().apply {
                        putParcelable(EXTRA_NAV_TYPE, navType)
                    })
                }
                startActivity(intent, options)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    // ============================================================================================
    //  Private setup methods
    // ============================================================================================

    private fun setupNavigationView() {
        nav_view.setNavigationItemSelectedListener { item ->
            drawer_layout.closeDrawer(GravityCompat.START)
            val fragment = when (item.itemId) {
                R.id.action_movies -> {
                    viewModel.setType(R.string.menu_movies, NavType.MOVIES)
                    inflateMenu(R.menu.bottom_navigation_menu_movie)
                    bottom_navigation.selectedItemId = movieItemId
                    movieFragment
                }
                R.id.action_tv_series -> {
                    viewModel.setType(R.string.menu_tv_series, NavType.TV_SERIES)
                    inflateMenu(R.menu.bottom_navigation_menu_tvshow)
                    bottom_navigation.selectedItemId = tvShowItemId
                    tvShowFragment
                }
                else -> throw RuntimeException("Unknown navType to replace fragment")
            }
            replaceFragmentInActivity(fragment.value, R.id.fragment_container)
            true
        }
    }

    private fun setupBottomNavigation() {
        bottom_navigation.apply {
            setOnNavigationItemSelectedListener { item ->
                val fragment = when (item.itemId) {
                    R.id.action_movie_popular -> getMovieFragment(
                        popularMoviesFragment,
                        R.id.action_movie_popular
                    )
                    R.id.action_movie_highest_rate -> getMovieFragment(
                        highRateMoviesFragment,
                        R.id.action_movie_highest_rate
                    )
                    R.id.action_movie_upcoming -> getMovieFragment(
                        upcomingMoviesFragment,
                        R.id.action_movie_upcoming
                    )
                    R.id.action_tvShow_popular -> getTvShowFragment(
                        popularTVShowFragment,
                        R.id.action_tvShow_popular
                    )
                    R.id.action_tvShow_highest_rate -> getTvShowFragment(
                        highRateTVShowFragment,
                        R.id.action_tvShow_highest_rate
                    )
                    R.id.action_tvShow_latest -> getTvShowFragment(
                        latestTVShowFragment,
                        R.id.action_tvShow_latest
                    )
                    else -> throw RuntimeException("Unknown sortType to replace fragment")
                }
                replaceFragmentInActivity(fragment, R.id.fragment_container)
                true
            }
        }
    }

    private fun inflateMenu(menuId: Int) {
        bottom_navigation.menu.clear()
        bottom_navigation.inflateMenu(menuId)
    }

    private fun getMovieFragment(fragment: Fragment, itemId: Int): Fragment {
        movieItemId = itemId
        movieFragment = lazy { fragment }
        return fragment
    }

    private fun getTvShowFragment(fragment: Fragment, itemId: Int): Fragment {
        tvShowItemId = itemId
        tvShowFragment = lazy { fragment }
        return fragment
    }
}
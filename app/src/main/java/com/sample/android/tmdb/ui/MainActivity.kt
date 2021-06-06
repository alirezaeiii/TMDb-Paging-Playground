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
    lateinit var popularTvShowFragment: PopularTVShowFragment

    @Inject
    lateinit var highRateTvShowFragment: HighRateTVShowFragment

    @Inject
    lateinit var latestTvShowFragment: LatestTVShowFragment

    private lateinit var viewModel: MainViewModel

    private lateinit var movieWrapper: FragmentWrapper
    private lateinit var tvShowWrapper: FragmentWrapper

    val navType: NavType?
        get() = viewModel.currentType.value

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)

        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.headline.observe(this, {
            title = it
        })

        if (savedInstanceState == null) {
            FragmentWrapper(popularMoviesFragment, R.id.action_movie_popular).also { movieWrapper = it }
            FragmentWrapper(popularTvShowFragment, R.id.action_tvShow_popular).also { tvShowWrapper = it }
            // Add popular movieFragment if this is first creation
            addFragmentToActivity(movieWrapper.fragment, R.id.fragment_container)
            nav_view.setCheckedItem(R.id.action_movies)
        } else {
            val movieItemId = savedInstanceState.getInt(MOVIE_ITEM_ID_KEY)
            val tvShowItemId = savedInstanceState.getInt(TV_SHOW_ITEM_ID_KEY)
            FragmentWrapper(getFragment(movieItemId), movieItemId).also { movieWrapper = it }
            FragmentWrapper(getFragment(tvShowItemId), tvShowItemId).also { tvShowWrapper = it }
            when (navType) {
                NavType.MOVIES -> inflateMenu(R.menu.bottom_navigation_menu_movie)
                NavType.TV_SERIES -> inflateMenu(R.menu.bottom_navigation_menu_tv_show)
            }
        }

        setupBottomNavigation()
        setupNavigationView()

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar,
            R.string.open_nav_drawer, R.string.close_nav_drawer
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(MOVIE_ITEM_ID_KEY, movieWrapper.itemId)
        outState.putInt(TV_SHOW_ITEM_ID_KEY, tvShowWrapper.itemId)
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
                    bottom_navigation.selectedItemId = movieWrapper.itemId
                    movieWrapper.fragment
                }
                R.id.action_tv_series -> {
                    viewModel.setType(R.string.menu_tv_series, NavType.TV_SERIES)
                    inflateMenu(R.menu.bottom_navigation_menu_tv_show)
                    bottom_navigation.selectedItemId = tvShowWrapper.itemId
                    tvShowWrapper.fragment
                }
                else -> throw RuntimeException("Unknown navType to replace fragment")
            }
            replaceFragmentInActivity(fragment, R.id.fragment_container)
            true
        }
    }

    private fun setupBottomNavigation() {
        bottom_navigation.apply {
            setOnNavigationItemSelectedListener { item ->
                replaceFragmentInActivity(getFragment(item.itemId), R.id.fragment_container)
                true
            }
        }
    }

    private fun inflateMenu(menuId: Int) {
        bottom_navigation.menu.clear()
        bottom_navigation.inflateMenu(menuId)
    }

    private fun getFragment(itemId: Int): Fragment {
        return when (itemId) {
            R.id.action_tvShow_popular -> getTvShowFragment(popularTvShowFragment, itemId)
            R.id.action_tvShow_highest_rate -> getTvShowFragment(highRateTvShowFragment, itemId)
            R.id.action_tvShow_latest -> getTvShowFragment(latestTvShowFragment, itemId)
            R.id.action_movie_popular -> getMovieFragment(popularMoviesFragment, itemId)
            R.id.action_movie_highest_rate -> getMovieFragment(highRateMoviesFragment, itemId)
            R.id.action_movie_upcoming -> getMovieFragment(upcomingMoviesFragment, itemId)
            else -> throw RuntimeException("Unknown sortType to replace fragment")
        }
    }

    private fun getMovieFragment(fragment: Fragment, itemId: Int): Fragment {
        if(::movieWrapper.isInitialized) {
            movieWrapper.setItem(fragment, itemId)
        }
        return fragment
    }

    private fun getTvShowFragment(fragment: Fragment, itemId: Int): Fragment {
        if(::tvShowWrapper.isInitialized) {
            tvShowWrapper.setItem(fragment, itemId)
        }
        return fragment
    }

    private class FragmentWrapper(var fragment: Fragment, var itemId: Int) {

        fun setItem(fragment: Fragment, itemId: Int) {
            this.fragment = fragment
            this.itemId = itemId
        }
    }

    companion object {
        private const val MOVIE_ITEM_ID_KEY = "movie_id"
        private const val TV_SHOW_ITEM_ID_KEY = "tvShow_id"
    }
}
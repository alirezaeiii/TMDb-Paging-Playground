package com.sample.android.tmdb.ui

import android.app.ActivityOptions
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.Intent.ACTION_SEARCH
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.sample.android.tmdb.util.NavType
import com.sample.android.tmdb.R
import com.sample.android.tmdb.ui.movie.HighRateMoviesFragment
import com.sample.android.tmdb.ui.movie.PopularMoviesFragment
import com.sample.android.tmdb.ui.movie.UpcomingMoviesFragment
import com.sample.android.tmdb.ui.search.SearchActivity
import com.sample.android.tmdb.ui.search.SearchActivity.Companion.EXTRA_NAV_TYPE
import com.sample.android.tmdb.ui.tvshow.HighRateTVShowFragment
import com.sample.android.tmdb.ui.tvshow.LatestTVShowFragment
import com.sample.android.tmdb.ui.tvshow.PopularTVShowFragment
import com.sample.android.tmdb.util.addFragmentToActivity
import com.sample.android.tmdb.util.replaceFragmentInActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_nav.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener {

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

    private lateinit var viewModel: MainViewModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)

        setSupportActionBar(toolbar)

        // Add movie detailFragment if this is first creation
        if (savedInstanceState == null) {
            addFragmentToActivity(popularMoviesFragment, R.id.fragment_container)
            nav_view.setCheckedItem(R.id.action_movies)
        }

        nav_view.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar,
                R.string.open_nav_drawer, R.string.close_nav_drawer
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        var fragment: Fragment

        bottom_navigation.apply {

            setOnNavigationItemSelectedListener {

                val navType = viewModel.currentType.value

                when (it.itemId) {
                    R.id.action_popular -> {
                        fragment = when (navType) {
                            NavType.MOVIES -> popularMoviesFragment
                            NavType.TV_SERIES -> popularTVShowFragment
                            else -> throw RuntimeException("Unknown navType")
                        }
                    }
                    R.id.action_highest_rate -> {
                        fragment = when (navType) {
                            NavType.MOVIES -> highRateMoviesFragment
                            NavType.TV_SERIES -> highRateTVShowFragment
                            else -> throw RuntimeException("Unknown navType")
                        }
                    }
                    R.id.action_upcoming -> {
                        fragment = when (navType) {
                            NavType.MOVIES -> upcomingMoviesFragment
                            NavType.TV_SERIES -> latestTVShowFragment
                            else -> throw RuntimeException("Unknown navType")
                        }
                    }
                    else -> throw RuntimeException("Unknown sortType to replace fragment")
                }
                replaceFragmentInActivity(fragment, R.id.fragment_container)
                true
            }
        }

        viewModel = ViewModelProviders.of(this)
                .get(MainViewModule::class.java)
        viewModel.headline.observe(this, Observer {
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
                val options = ActivityOptions.makeSceneTransitionAnimation(this,
                        searchMenuView, getString(R.string.transition_search_back)).toBundle()

                val intent = Intent(this,
                        SearchActivity::class.java).apply {
                    action = ACTION_SEARCH
                    putExtras(Bundle().apply {
                        putParcelable(EXTRA_NAV_TYPE, viewModel.currentType.value)
                    })
                }
                startActivity(intent, options)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawer_layout.closeDrawer(GravityCompat.START)
        bottom_navigation.selectedItemId = R.id.action_popular
        val fragment = when (item.itemId) {
            R.id.action_movies -> {
                viewModel.setNavType(NavType.MOVIES)
                viewModel.setHeadline(R.string.menu_movies)
                popularMoviesFragment
            }
            R.id.action_tv_series -> {
                viewModel.setNavType(NavType.TV_SERIES)
                viewModel.setHeadline(R.string.menu_tv_series)
                popularTVShowFragment
            }
            else -> throw RuntimeException("Unknown navType to replace fragment")
        }
        replaceFragmentInActivity(fragment, R.id.fragment_container)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun getNavType() = viewModel.currentType.value
}
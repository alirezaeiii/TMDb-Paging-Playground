package com.sample.android.tmdb.ui

import android.app.ActivityOptions
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.Intent.ACTION_SEARCH
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.sample.android.tmdb.util.NavType
import com.sample.android.tmdb.R
import com.sample.android.tmdb.ui.item.movie.HighRateMoviesFragment
import com.sample.android.tmdb.ui.item.movie.PopularMoviesFragment
import com.sample.android.tmdb.ui.item.movie.UpcomingMoviesFragment
import com.sample.android.tmdb.ui.search.EXTRA_NAV_TYPE
import com.sample.android.tmdb.ui.search.SearchActivity
import com.sample.android.tmdb.ui.item.tvshow.HighRateTVShowFragment
import com.sample.android.tmdb.ui.item.tvshow.LatestTVShowFragment
import com.sample.android.tmdb.ui.item.tvshow.PopularTVShowFragment
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)

        setSupportActionBar(toolbar)

        // Add movie detailFragment if this is first creation
        if (savedInstanceState == null) {
            addFragmentToActivity(popularMoviesFragment, R.id.fragment_container)
            nav_view.setCheckedItem(R.id.action_movies)
        }

        nav_view.setNavigationItemSelectedListener { item ->
            drawer_layout.closeDrawer(GravityCompat.START)
            bottom_navigation.selectedItemId = R.id.action_popular
            val fragment = when (item.itemId) {
                R.id.action_movies -> {
                    viewModel.setType(R.string.menu_movies, NavType.MOVIES)
                    popularMoviesFragment
                }
                R.id.action_tv_series -> {
                    viewModel.setType(R.string.menu_tv_series, NavType.TV_SERIES)
                    popularTVShowFragment
                }
                else -> throw RuntimeException("Unknown navType to replace fragment")
            }
            replaceFragmentInActivity(fragment, R.id.fragment_container)
            true
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar,
                R.string.open_nav_drawer, R.string.close_nav_drawer
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        var fragment: Fragment

        bottom_navigation.apply {

            setOnNavigationItemSelectedListener { item ->

                when (item.itemId) {
                    R.id.action_popular -> {
                        fragment = when (getNavType()) {
                            NavType.MOVIES -> popularMoviesFragment
                            NavType.TV_SERIES -> popularTVShowFragment
                            else -> throw RuntimeException("Unknown navType")
                        }
                    }
                    R.id.action_highest_rate -> {
                        fragment = when (getNavType()) {
                            NavType.MOVIES -> highRateMoviesFragment
                            NavType.TV_SERIES -> highRateTVShowFragment
                            else -> throw RuntimeException("Unknown navType")
                        }
                    }
                    R.id.action_upcoming -> {
                        fragment = when (getNavType()) {
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

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
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
                        putParcelable(EXTRA_NAV_TYPE, getNavType())
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

    fun getNavType() = viewModel.currentType.value
}
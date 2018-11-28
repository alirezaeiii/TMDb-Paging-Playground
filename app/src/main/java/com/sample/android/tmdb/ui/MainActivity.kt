package com.sample.android.tmdb.ui

import android.app.ActivityOptions
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.sample.android.tmdb.R
import com.sample.android.tmdb.addFragmentToActivity
import com.sample.android.tmdb.replaceFragmentInActivity
import com.sample.android.tmdb.ui.base.HighRateMoviesFragment
import com.sample.android.tmdb.ui.base.PopularMoviesFragment
import com.sample.android.tmdb.ui.base.UpcomingMoviesFragment
import com.sample.android.tmdb.ui.search.SearchActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var popularMoviesFragment: PopularMoviesFragment

    @Inject
    lateinit var highRateMoviesFragment: HighRateMoviesFragment

    @Inject
    lateinit var upcomingMoviesFragment: UpcomingMoviesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        // Add movie detailFragment if this is first creation
        if (savedInstanceState == null) {

            addFragmentToActivity(popularMoviesFragment, R.id.fragment_container)
        }

        var fragment: Fragment

        bottom_navigation.apply {

            setOnNavigationItemSelectedListener {

                fragment = when (it.itemId) {
                    R.id.action_popular -> popularMoviesFragment
                    R.id.action_highest_rate -> highRateMoviesFragment
                    R.id.action_upcoming -> upcomingMoviesFragment
                    else -> throw RuntimeException("Unknown sortType to replace detailFragment")
                }
                replaceFragmentInActivity(fragment, R.id.fragment_container)
                true
            }
        }
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

                val intent = android.content.Intent(this,
                        SearchActivity::class.java).apply {
                    action = android.content.Intent.ACTION_SEARCH
                }
                startActivity(intent, options)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

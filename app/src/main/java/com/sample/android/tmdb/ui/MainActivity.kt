package com.sample.android.tmdb.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.sample.android.tmdb.R
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

        // Add movie detailFragment if this is first creation
        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, popularMoviesFragment).commit()
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
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment).commit()
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
                val intent = Intent(this, SearchActivity::class.java)
                intent.action = Intent.ACTION_SEARCH
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

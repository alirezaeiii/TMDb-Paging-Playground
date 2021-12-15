package com.sample.android.tmdb.ui.feed

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.ActivityMainBinding
import com.sample.android.tmdb.ui.feed.movie.FeedMovieFragment
import com.sample.android.tmdb.ui.feed.tvshow.FeedTVShowFragment
import com.sample.android.tmdb.ui.paging.search.SearchActivity
import com.sample.android.tmdb.util.Constants.EXTRA_NAV_TYPE
import com.sample.android.tmdb.util.addFragmentToActivity
import com.sample.android.tmdb.util.replaceFragmentInActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class FeedActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var feedMovieFragment: FeedMovieFragment

    @Inject
    lateinit var feedTVShowFragment: FeedTVShowFragment

    private val viewModel: MainViewModel by viewModels()

    private var _binding: ActivityMainBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        viewModel.headline.observe(this, {
            title = it
        })
        if(savedInstanceState == null) {
            addFragmentToActivity(feedMovieFragment, R.id.fragment_container)
            nav_view.setCheckedItem(R.id.action_movies)
        }
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
                    action = Intent.ACTION_SEARCH
                    putExtras(Bundle().apply {
                        putParcelable(EXTRA_NAV_TYPE, viewModel.currentType.value)
                    })
                }
                startActivity(intent, options)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupNavigationView() {
        binding.navView.setNavigationItemSelectedListener { item ->
            drawer_layout.closeDrawer(GravityCompat.START)
            val fragment = when (item.itemId) {
                R.id.action_movies -> {
                    viewModel.setType(R.string.menu_movies, NavType.MOVIES)
                    feedMovieFragment
                }
                R.id.action_tv_series -> {
                    viewModel.setType(R.string.menu_tv_series, NavType.TV_SERIES)
                    feedTVShowFragment

                }
                else -> throw RuntimeException("Unknown item to replace fragment")
            }
            replaceFragmentInActivity(fragment, R.id.fragment_container)
            true
        }
    }
}
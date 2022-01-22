package com.sample.android.tmdb.ui.feed

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.ActivityFeedBinding
import com.sample.android.tmdb.ui.BaseActivity
import com.sample.android.tmdb.ui.feed.movie.FeedMovieFragment
import com.sample.android.tmdb.ui.feed.tvshow.FeedTVShowFragment
import com.sample.android.tmdb.util.addFragmentToActivity
import com.sample.android.tmdb.util.replaceFragmentInActivity
import javax.inject.Inject

class FeedActivity : BaseActivity() {

    @Inject
    lateinit var feedMovieFragment: FeedMovieFragment

    @Inject
    lateinit var feedTVShowFragment: FeedTVShowFragment

    private val viewModel: MainViewModel by viewModels()

    private var _binding: ActivityFeedBinding? = null

    private val binding get() = _binding!!

    override val navType: NavType?
        get() = viewModel.currentType.value

    override val toolbar: Toolbar
        get() = binding.toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        viewModel.headline.observe(this, {
            title = it
        })
        if (savedInstanceState == null) {
            addFragmentToActivity(feedMovieFragment, R.id.fragment_container)
            binding.navView.setCheckedItem(R.id.action_movies)
        }
        setupNavigationView()

        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.toolbar,
            R.string.open_nav_drawer, R.string.close_nav_drawer
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupNavigationView() {
        binding.navView.setNavigationItemSelectedListener { item ->
            binding.drawerLayout.closeDrawer(GravityCompat.START)
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
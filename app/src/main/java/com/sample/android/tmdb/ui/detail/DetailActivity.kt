package com.sample.android.tmdb.ui.detail

import android.os.Bundle
import android.view.MenuItem
import com.sample.android.tmdb.NavType
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import com.sample.android.tmdb.R
import com.sample.android.tmdb.ui.detail.movie.MovieDetailFragment
import com.sample.android.tmdb.ui.detail.tvshow.TVShowDetailFragment
import com.sample.android.tmdb.util.addFragmentToActivity

class DetailActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var movieDetailFragment: MovieDetailFragment

    @Inject
    lateinit var tvShowDetailFragment: TVShowDetailFragment

    @Inject
    lateinit var navType: NavType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (savedInstanceState == null) {

            when (navType) {

                NavType.MOVIES -> supportFragmentManager.findFragmentById(R.id.fragment_container)
                        as MovieDetailFragment? ?: movieDetailFragment.also {
                    addFragmentToActivity(it, R.id.fragment_container)
                }

                NavType.TV_SERIES -> supportFragmentManager.findFragmentById(R.id.fragment_container)
                        as TVShowDetailFragment? ?: tvShowDetailFragment.also {
                    addFragmentToActivity(it, R.id.fragment_container)
                }
            }
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

    companion object {

        const val EXTRA_ITEM = "item"
        const val EXTRA_NAV_TYPE = "nav_type"
    }
}
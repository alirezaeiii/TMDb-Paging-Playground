package com.sample.android.tmdb.ui.detail

import android.os.Bundle
import android.support.v4.app.Fragment
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
            val fragment: Fragment
            when (navType) {
                NavType.MOVIES -> fragment = movieDetailFragment
                NavType.TV_SERIES -> fragment = tvShowDetailFragment
            }
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

    companion object {
        const val EXTRA_MOVIE = "movie"
        const val EXTRA_TV_SHOW = "tv_show"
        const val EXTRA_NAV_TYPE = "nav_type"
    }
}
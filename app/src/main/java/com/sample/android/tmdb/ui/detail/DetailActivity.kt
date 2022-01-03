package com.sample.android.tmdb.ui.detail

import android.os.Bundle
import android.view.MenuItem
import com.sample.android.tmdb.R
import com.sample.android.tmdb.ui.detail.movie.MovieDetailFragment
import com.sample.android.tmdb.ui.detail.tvshow.TVShowDetailFragment
import com.sample.android.tmdb.ui.feed.NavType
import com.sample.android.tmdb.util.addFragmentToActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

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
            val fragment = when (navType) {
                NavType.MOVIES -> movieDetailFragment
                NavType.TV_SERIES -> tvShowDetailFragment
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
}
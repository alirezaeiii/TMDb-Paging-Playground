package com.sample.android.tmdb.ui.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialContainerTransformSharedElementCallback
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
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        // set up shared element transition
        setEnterSharedElementCallback(
            MaterialContainerTransformSharedElementCallback()
        )
        window.sharedElementEnterTransition = getContentTransform()
        window.sharedElementReturnTransition = getContentTransform()

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

    /** get a material container arc transform. */
    private fun getContentTransform(): MaterialContainerTransform {
        return MaterialContainerTransform().apply {
            addTarget(R.id.details_poster)
            duration = 450
            pathMotion = MaterialArcMotion()
        }
    }
}
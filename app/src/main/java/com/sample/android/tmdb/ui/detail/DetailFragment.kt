package com.sample.android.tmdb.ui.detail

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.transition.Transition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.FragmentDetailBinding
import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.setupActionBar
import com.sample.android.tmdb.ui.MovieViewHolder.Companion.BASE_BACKDROP_PATH
import com.sample.android.tmdb.vo.Movie
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_detail.view.*
import javax.inject.Inject

@ActivityScoped
class DetailFragment @Inject
constructor() // Required empty public constructor
    : DaggerFragment() {

    @Inject
    lateinit var movie: Movie

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_detail, container, false)
        binding = FragmentDetailBinding.bind(root).apply {
            movie = this@DetailFragment.movie
        }

        with(root) {

            with(activity as AppCompatActivity) {
                setupActionBar(toolbar) {
                    setDisplayHomeAsUpEnabled(true)
                    setDisplayShowHomeEnabled(true)
                }
            }

            ViewCompat.setTransitionName(movie_poster, VIEW_NAME_HEADER_IMAGE)
            ViewCompat.setTransitionName(movie_name, VIEW_NAME_HEADER_TITLE)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && addTransitionListener()) {
                // If we're running on Lollipop and we have added a listener to the shared element
                // transition, load the thumbnail. The listener will load the full-size image when
                // the transition is complete.

                Glide.with(movie_poster.context)
                        .load("$BASE_BACKDROP_PATH${movie.backdropPath}")
                        .into(movie_poster)
            } else {
                // If all other cases we should just load the full-size image now
                Glide.with(movie_poster.context)
                        .load("$BASE_BACKDROP_PATH${movie.backdropPath}")
                        .into(movie_poster)
            }
        }

        return root
    }

    /**
     * Try and add a [Transition.TransitionListener] to the entering shared element
     * [Transition]. We do this so that we can load the full-size image after the transition
     * has completed.
     *
     * @return true if we were successful in adding a listener to the enter transition
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun addTransitionListener(): Boolean {
        val transition = requireActivity().window.sharedElementEnterTransition

        if (transition != null) {
            // There is an entering shared element transition so add a listener to it
            transition.addListener(object : Transition.TransitionListener {
                override fun onTransitionEnd(transition: Transition) {
                    // As the transition has ended, we can now load the full-size image
                    with(binding) {
                        Glide.with(moviePoster.context)
                                .load("$BASE_BACKDROP_PATH${movie?.backdropPath}")
                                .into(moviePoster)
                    }

                    // Make sure we remove ourselves as a listener
                    transition.removeListener(this)
                }

                override fun onTransitionStart(transition: Transition) {
                    // No-op
                }

                override fun onTransitionCancel(transition: Transition) {
                    // Make sure we remove ourselves as a listener
                    transition.removeListener(this)
                }

                override fun onTransitionPause(transition: Transition) {
                    // No-op
                }

                override fun onTransitionResume(transition: Transition) {
                    // No-op
                }
            })
            return true
        }

        // If we reach here then we have not added a listener
        return false
    }

    companion object {

        // View name of the header image. Used for activity scene transitions
        const val VIEW_NAME_HEADER_IMAGE = "detail:header:image"

        // View name of the header title. Used for activity scene transitions
        const val VIEW_NAME_HEADER_TITLE = "detail:header:title"
    }
}
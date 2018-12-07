package com.sample.android.tmdb.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.constraint.motion.MotionLayout
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.FragmentDetailBinding
import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.setupActionBar
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
                setupActionBar(details_toolbar) {
                    setDisplayShowTitleEnabled(false)
                    setDisplayHomeAsUpEnabled(true)
                    setDisplayShowHomeEnabled(true)
                }
            }

            // Make the MotionLayout draw behind the status bar
            details_motion.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.detailsMotion.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionChange(motionLayout: MotionLayout, startId: Int, endId: Int, progress: Float) {

                binding.detailsAppbarBackground.cutProgress = 1f - progress

                binding.detailsPoster.visibility = View.VISIBLE
            }

            @SuppressLint("RestrictedApi")
            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                when (currentId) {
                    R.id.end -> {
                        binding.detailsAppbarBackground.cutProgress = 0f
                        binding.detailsPoster.visibility = View.GONE
                    }
                    R.id.start -> {
                        binding.detailsAppbarBackground.cutProgress = 1f
                        binding.detailsPoster.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}
package com.sample.android.tmdb.ui.detail

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.constraint.motion.MotionLayout
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.android.tmdb.BR
import com.sample.android.tmdb.R
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.BaseDaggerFragment
import com.sample.android.tmdb.ui.person.PERSON_WRAPPER
import com.sample.android.tmdb.ui.person.PersonActivity
import com.sample.android.tmdb.ui.person.PersonWrapper
import com.sample.android.tmdb.util.setupActionBar
import com.sample.android.tmdb.util.visibleGone
import kotlinx.android.synthetic.main.fragment_detail_movie.view.*
import kotlinx.android.synthetic.main.trailers.view.*

abstract class DetailFragment<T : TmdbItem> : BaseDaggerFragment() {

    protected abstract val layoutId: Int

    protected abstract val viewModel: DetailViewModel

    protected abstract val tmdbItem: T

    protected abstract fun getViewBinding(root: View): ViewDataBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(layoutId, container, false)
        getViewBinding(root).apply {
            setVariable(BR.vm, viewModel)
            lifecycleOwner = viewLifecycleOwner
        }

        viewModel.cast.observe(this, Observer {
            it?.let {
                root.cast_list.apply {
                    setHasFixedSize(true)
                    adapter = CastAdapter(it, object : CastClickCallback {
                        override fun onClick(personId: Int, personName: String, profilePath: String?) {
                            val intent = Intent(activity, PersonActivity::class.java).apply {
                                putExtras(Bundle().apply {
                                    putParcelable(PERSON_WRAPPER, PersonWrapper(personId,
                                            personName,
                                            profilePath,
                                            tmdbItem.backdropPath))
                                })
                            }
                            startActivity(intent)
                        }
                    })
                }
            }
        })

        with(root) {

            viewModel.trailers.observe(viewLifecycleOwner, Observer {
                it?.let {
                    trailers_label.visibleGone(it.isNotEmpty())
                }
            })

            viewModel.cast.observe(viewLifecycleOwner, Observer {
                it?.let {
                    cast_label.visibleGone(it.isNotEmpty())
                }
            })

            with(activity as AppCompatActivity) {
                setupActionBar(details_toolbar) {
                    setDisplayShowTitleEnabled(false)
                    setDisplayHomeAsUpEnabled(true)
                    setDisplayShowHomeEnabled(true)
                }
            }

            summary_label.visibleGone(tmdbItem.overview.trim().isNotEmpty())

            // Make the MotionLayout draw behind the status bar
            details_motion.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE

            summary.setOnClickListener {
                val maxLine = resources.getInteger(R.integer.max_lines)
                summary.maxLines = if (summary.maxLines > maxLine) maxLine else Int.MAX_VALUE
            }

            with(details_rv) {
                postDelayed({ scrollTo(0, 0) }, 100)
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.details_motion.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionChange(motionLayout: MotionLayout, startId: Int, endId: Int, progress: Float) {

                view.details_appbar_background.cutProgress = 1f - progress
                view.details_poster.visibility = View.VISIBLE
            }

            @SuppressLint("RestrictedApi")
            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                when (currentId) {
                    R.id.end -> {
                        view.details_appbar_background.cutProgress = 0f
                        view.details_poster.visibility = View.GONE
                    }
                    R.id.start -> {
                        view.details_appbar_background.cutProgress = 1f
                        view.details_poster.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}
package com.sample.android.tmdb.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import com.sample.android.tmdb.BR
import com.sample.android.tmdb.R
import com.sample.android.tmdb.domain.Cast
import com.sample.android.tmdb.domain.Crew
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.util.setupActionBar
import com.sample.android.tmdb.util.visibleGone
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_detail_movie.view.*


abstract class DetailFragment<T : TmdbItem> : DaggerFragment() {

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

        with(root) {

            viewModel.creditWrapper.observe(viewLifecycleOwner, Observer { creditWrapper ->
                fragmentManager?.let {
                    val myAdapter = ViewPagerFragmentAdapter(it, lifecycle)

                    val castPagerItem = PagerItem(CreditFragment.newInstance(tmdbItem,
                            creditWrapper.cast as ArrayList<Cast>), R.string.cast)
                    val crewPagerItem = PagerItem(CreditFragment.newInstance(tmdbItem,
                            creditWrapper.crew as ArrayList<Crew>), R.string.crew)

                    myAdapter.addFragment(castPagerItem)
                    myAdapter.addFragment(crewPagerItem)

                    pager.adapter = myAdapter
                    TabLayoutMediator(tab_layout, pager) { tab, position ->
                        tab.text = getString(myAdapter.creditFragments[position].title)
                    }.attach()
                }
            })

            with(activity as AppCompatActivity) {
                setupActionBar(details_toolbar) {
                    setDisplayShowTitleEnabled(false)
                    setDisplayHomeAsUpEnabled(true)
                    setDisplayShowHomeEnabled(true)
                }
            }

            visibleGone(summary_label, tmdbItem.overview.trim().isNotEmpty())

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
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {}

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}

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
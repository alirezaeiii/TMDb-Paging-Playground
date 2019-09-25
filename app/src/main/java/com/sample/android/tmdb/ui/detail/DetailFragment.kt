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
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.ui.person.PersonActivity
import com.sample.android.tmdb.ui.person.PersonActivity.Companion.EXTRA_PERSON
import com.sample.android.tmdb.ui.person.PersonExtra
import com.sample.android.tmdb.util.setupActionBar
import com.sample.android.tmdb.util.visibleGone
import com.sample.android.tmdb.vo.TmdbItem
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_detail_movie.view.*
import javax.inject.Inject

abstract class DetailFragment<T : TmdbItem>
    : DaggerFragment(), CastClickCallback {

    @Inject
    lateinit var dataSource: MoviesRemoteDataSource

    protected lateinit var viewModel: DetailViewModel

    protected abstract fun initViewModel()

    protected abstract fun getLayoutId(): Int

    protected abstract fun initViewBinding(root: View): ViewDataBinding

    protected abstract fun getTmdbItem(): T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        initViewModel()

        val root = inflater.inflate(getLayoutId(), container, false)

        initViewBinding(root).apply {
            setVariable(BR.vm, viewModel)
            lifecycleOwner = viewLifecycleOwner
        }

        viewModel.showTrailers(getTmdbItem())
        viewModel.showCast(getTmdbItem())

        with(root) {

            with(activity as AppCompatActivity) {
                setupActionBar(details_toolbar) {
                    setDisplayShowTitleEnabled(false)
                    setDisplayHomeAsUpEnabled(true)
                    setDisplayShowHomeEnabled(true)
                }
            }

            summary_label.visibleGone(getTmdbItem().overview.trim().isNotEmpty())

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

        viewModel.cast.observe(this, Observer {
            it?.apply {
                val adapter = CastAdapter(it, this@DetailFragment)
                view.cast_list.apply {
                    setHasFixedSize(true)
                    cast_list.adapter = adapter
                }
            }
        })
    }

    override fun onClick(personId: Int, personName: String, profilePath: String?) {
        val intent = Intent(activity, PersonActivity::class.java).apply {
            putExtras(Bundle().apply {
                putParcelable(EXTRA_PERSON, PersonExtra(personId,
                        personName,
                        profilePath,
                        getTmdbItem().backdropPath))
            })
        }
        startActivity(intent)
    }
}
package com.sample.android.tmdb.ui.detail

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.constraint.motion.MotionLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sample.android.tmdb.BR
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.FragmentDetailBinding
import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.setupActionBar
import com.sample.android.tmdb.ui.person.PersonActivity
import com.sample.android.tmdb.ui.person.PersonActivity.Companion.EXTRA_PERSON
import com.sample.android.tmdb.ui.person.PersonExtra
import com.sample.android.tmdb.vo.Movie
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_detail.view.*
import javax.inject.Inject

@ActivityScoped
class DetailFragment @Inject
constructor() // Required empty public constructor
    : DaggerFragment(), CastClickCallback {

    @Inject
    lateinit var dataSource: MoviesRemoteDataSource

    @Inject
    lateinit var movie: Movie

    private lateinit var binding: FragmentDetailBinding

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieDetailViewModel(requireActivity().application, dataSource) as T
            }
        })[MovieDetailViewModel::class.java]

        val root = inflater.inflate(R.layout.fragment_detail, container, false)
        binding = FragmentDetailBinding.bind(root).apply {
            movie = this@DetailFragment.movie
            setVariable(BR.vm, viewModel)
            setLifecycleOwner(this@DetailFragment)
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

        binding.vm?.showTrailers(movie)
        binding.vm?.showCast(movie)

        viewModel.cast.observe(this@DetailFragment, Observer {

            val adapter = CastAdapter(it!!, this@DetailFragment)

            binding.root.cast_list.apply {

                setHasFixedSize(true)

                cast_list.adapter = adapter
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.compositeDisposable.clear()
    }

    override fun onClick(personId: Int, personName: String, profilePath: String?,
                         poster: ImageView, name: TextView) {
        val intent = Intent(activity, PersonActivity::class.java).apply {
            putExtras(Bundle().apply {
                putParcelable(EXTRA_PERSON, PersonExtra(personId,
                        personName,
                        profilePath,
                        movie.backdropPath))
            })
        }
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(),

                Pair<View, String>(poster, ViewCompat.getTransitionName(poster)),
                Pair<View, String>(name, ViewCompat.getTransitionName(name)))

        ActivityCompat.startActivity(requireContext(), intent, activityOptions.toBundle())
    }
}
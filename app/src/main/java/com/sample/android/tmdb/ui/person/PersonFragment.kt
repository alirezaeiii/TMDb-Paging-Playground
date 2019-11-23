package com.sample.android.tmdb.ui.person

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sample.android.tmdb.BR
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.FragmentPersonBinding
import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.ui.BaseDaggerFragment
import com.sample.android.tmdb.util.BindingsAdapter.BASE_BACKDROP_PATH
import com.sample.android.tmdb.util.BindingsAdapter.IMAGE_LOW_RES_BASE_URL
import com.sample.android.tmdb.util.visibleGone
import kotlinx.android.synthetic.main.fragment_person.view.*
import kotlinx.android.synthetic.main.person_header.view.*
import javax.inject.Inject

@ActivityScoped
class PersonFragment @Inject
constructor() // Required empty public constructor
    : BaseDaggerFragment() {

    @Inject
    lateinit var person: PersonExtra

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val viewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return PersonViewModel(dataSource, person.personId) as T
            }
        })[PersonViewModel::class.java]

        val root = inflater.inflate(R.layout.fragment_person, container, false)
        val binding = FragmentPersonBinding.bind(root).apply {
            setVariable(BR.vm, viewModel)
            lifecycleOwner = viewLifecycleOwner
        }

        with(binding.personHeader) {

            Glide.with(context)
                    .load("$IMAGE_LOW_RES_BASE_URL${person.profilePath}")
                    .apply(RequestOptions()
                            .error(R.drawable.ic_error_outline_white_96dp))
                    .into(icon)

            name.text = person.personName

            Glide.with(context)
                    .load("$BASE_BACKDROP_PATH${person.backdropPath}")
                    .into(image_background)
        }

        with(root) {

            search_back.setOnClickListener {
                search_back.background = null
                activity?.finishAfterTransition()
            }

            viewModel.person.observe(this@PersonFragment, Observer {

                biography_label.visibleGone(it?.biography?.trim() != "")
            })

            biography.setOnClickListener {
                val maxLine = resources.getInteger(R.integer.max_lines)
                biography.maxLines = if (biography.maxLines > maxLine) maxLine else Int.MAX_VALUE
            }

            viewModel.knownAs.observe(this@PersonFragment, Observer {

                it?.let {
                    known_as.visibleGone(it.isNotEmpty())
                    known_as.text = getString(R.string.known_as, it)
                }
            })
        }

        return root
    }
}

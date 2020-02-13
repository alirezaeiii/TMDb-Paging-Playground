package com.sample.android.tmdb.ui.person

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.android.tmdb.BR
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.FragmentPersonBinding
import com.sample.android.tmdb.util.visibleGone
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_person.view.*
import kotlinx.android.synthetic.main.person_header.view.*
import javax.inject.Inject

class PersonFragment @Inject
constructor() // Required empty public constructor
    : DaggerFragment() {

    @Inject
    lateinit var factory: PersonViewModel.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val viewModel = ViewModelProviders.of(this, factory).get(PersonViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_person, container, false)
        FragmentPersonBinding.bind(root).apply {
            setVariable(BR.vm, viewModel)
            person = factory.person
            lifecycleOwner = viewLifecycleOwner
        }

        with(root) {

            back.setOnClickListener {
                activity?.finish()
            }

            viewModel.person.observe(viewLifecycleOwner, Observer {
                it?.let {
                    biography_label.visibleGone(it.biography.trim().isNotEmpty())
                }
            })

            biography.setOnClickListener {
                val maxLine = resources.getInteger(R.integer.max_lines)
                biography.maxLines = if (biography.maxLines > maxLine) maxLine else Int.MAX_VALUE
            }

            viewModel.knownAs.observe(viewLifecycleOwner, Observer {
                it?.let {
                    known_as.visibleGone(it.isNotEmpty())
                    known_as.text = getString(R.string.known_as, it)
                }
            })
        }
        return root
    }
}

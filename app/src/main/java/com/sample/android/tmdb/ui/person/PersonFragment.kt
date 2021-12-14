package com.sample.android.tmdb.ui.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.FragmentPersonBinding
import com.sample.android.tmdb.ui.BaseDetailFragment
import com.sample.android.tmdb.util.toVisibility
import javax.inject.Inject

class PersonFragment @Inject
constructor() // Required empty public constructor
    : BaseDetailFragment<PersonViewModel, FragmentPersonBinding>(R.layout.fragment_person) {

    @Inject
    lateinit var factory: PersonViewModel.Factory

    @Inject
    lateinit var personWrapper: PersonWrapper

    override val viewModel: PersonViewModel by lazy {
        ViewModelProvider(this, factory).get(PersonViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        with(binding) {
            person = personWrapper
            personHeader.backBtn.setOnClickListener {
                activity?.finish()
            }

            viewModel.liveData.observe(viewLifecycleOwner, {
                biographyLabel.toVisibility(it.biography.trim().isNotEmpty())
                knownAs.toVisibility(it.alsoKnowAs.isNotEmpty())
                knownAs.text = getString(R.string.known_as, it.alsoKnowAs.joinToString())
            })
            biography.setOnClickListener {
                val maxLine = resources.getInteger(R.integer.max_lines)
                biography.maxLines = if (biography.maxLines > maxLine) maxLine else Int.MAX_VALUE
            }
        }
        return binding.root
    }
}

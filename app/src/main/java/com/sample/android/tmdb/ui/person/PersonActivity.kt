package com.sample.android.tmdb.ui.person

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.ActivityDetailBinding
import com.sample.android.tmdb.ui.BaseActivity
import com.sample.android.tmdb.util.addFragmentToActivity
import javax.inject.Inject

class PersonActivity : BaseActivity() {

    @Inject
    lateinit var personFragment: PersonFragment

    private var _binding: ActivityDetailBinding? = null

    private val binding get() = _binding!!

    override val networkStatusLayout: View?
        get() = _binding?.networkStatusLayout

    override val textViewNetworkStatus: TextView
        get() = binding.textViewNetworkStatus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {

            supportFragmentManager.findFragmentById(R.id.fragment_container)
                    as PersonFragment? ?: personFragment.also {
                addFragmentToActivity(it, R.id.fragment_container)
            }
        }
    }
}

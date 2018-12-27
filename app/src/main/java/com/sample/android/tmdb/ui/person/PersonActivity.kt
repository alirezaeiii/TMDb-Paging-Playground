package com.sample.android.tmdb.ui.person

import android.os.Bundle
import android.os.Parcelable
import android.view.MenuItem
import com.sample.android.tmdb.R
import com.sample.android.tmdb.addFragmentToActivity
import com.sample.android.tmdb.ui.detail.DetailFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

class PersonActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var personFragment: PersonFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (savedInstanceState == null) {

            supportFragmentManager.findFragmentById(R.id.fragment_container)
                    as DetailFragment? ?: personFragment.also {
                addFragmentToActivity(it, R.id.fragment_container)
            }
        }
    }

    companion object {

        const val EXTRA_PERSON = "person"
    }
}

@Parcelize
class PersonExtra(
        val personId: Int,
        val personName: String,
        val profilePath: String?,
        val backdropPath: String?) : Parcelable

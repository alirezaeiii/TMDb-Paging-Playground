package com.sample.android.tmdb.ui.person

import com.sample.android.tmdb.ui.BaseDetailActivity
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PersonActivity : BaseDetailActivity() {

    @Inject
    lateinit var personFragment: PersonFragment

    override val fragment: DaggerFragment
        get() = personFragment
}

package com.sample.android.tmdb.ui

import com.sample.android.tmdb.usecase.UseCase
import dagger.android.support.DaggerFragment
import javax.inject.Inject

open class BaseDaggerFragment : DaggerFragment() {

    @Inject
    lateinit var useCase: UseCase
}
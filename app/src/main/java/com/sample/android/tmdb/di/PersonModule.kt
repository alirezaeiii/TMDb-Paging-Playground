package com.sample.android.tmdb.di

import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.domain.model.Credit
import com.sample.android.tmdb.ui.person.PersonActivity
import com.sample.android.tmdb.ui.person.PersonFragment
import com.sample.android.tmdb.ui.person.PersonViewModel
import com.sample.android.tmdb.util.Constants.CREDIT
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class PersonModule {

    @ContributesAndroidInjector
    internal abstract fun personFragment(): PersonFragment

    @Binds
    internal abstract fun bindViewModelFactory(factory: PersonViewModel.Factory): ViewModelProvider.Factory

    @Module
    companion object {
        @Provides
        @JvmStatic
        internal fun providePersonWrapper(activity: PersonActivity): Credit =
                activity.intent.extras?.getParcelable(CREDIT)!!
    }
}
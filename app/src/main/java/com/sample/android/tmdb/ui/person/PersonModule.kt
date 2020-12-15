package com.sample.android.tmdb.ui.person

import androidx.lifecycle.ViewModelProvider
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
        internal fun providePersonWrapper(activity: PersonActivity): PersonWrapper =
                activity.intent.extras?.getParcelable(PERSON_WRAPPER)!!
    }
}
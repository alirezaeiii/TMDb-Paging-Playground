package com.sample.android.tmdb.ui.person

import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.di.FragmentScoped
import com.sample.android.tmdb.ui.person.PersonActivity.Companion.EXTRA_PERSON
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class PersonModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun personFragment(): PersonFragment

    @Module
    companion object {
        @Provides
        @ActivityScoped
        @JvmStatic
        internal fun providePersonExtra(activity: PersonActivity): PersonExtra =
                activity.intent.extras.getParcelable(EXTRA_PERSON)
    }
}
package com.sample.android.tmdb.ui.person

import com.sample.android.tmdb.ui.person.PersonActivity.Companion.EXTRA_PERSON
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class PersonModule {

    @ContributesAndroidInjector
    internal abstract fun personFragment(): PersonFragment

    @Module
    companion object {
        @Provides
        @JvmStatic
        internal fun providePersonExtra(activity: PersonActivity): PersonExtra =
                activity.intent.extras.getParcelable(EXTRA_PERSON)
    }
}
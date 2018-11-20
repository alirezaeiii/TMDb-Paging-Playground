package com.sample.android.tmdb.ui.detail;

import com.sample.android.tmdb.di.ActivityScoped;
import com.sample.android.tmdb.di.FragmentScoped;
import com.sample.android.tmdb.ui.detail.DetailActivity;
import com.sample.android.tmdb.ui.detail.DetailFragment;
import com.sample.android.tmdb.vo.Movie;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

import static com.sample.android.tmdb.ui.detail.DetailActivity.EXTRA_MOVIE;

@Module
public abstract class DetailModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract DetailFragment detailFragment();

    @Provides
    @ActivityScoped
    static Movie provideMovie(DetailActivity activity) {
        return activity.getIntent().getExtras().getParcelable(EXTRA_MOVIE);
    }
}

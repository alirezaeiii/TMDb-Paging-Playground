package com.sample.android.tmdb.di

import com.sample.android.tmdb.di.movie.*
import com.sample.android.tmdb.di.tvshow.*
import com.sample.android.tmdb.ui.detail.movie.DetailMovieActivity
import com.sample.android.tmdb.ui.detail.tvshow.DetailTVShowActivity
import com.sample.android.tmdb.ui.feed.FeedActivity
import com.sample.android.tmdb.ui.paging.main.movie.*
import com.sample.android.tmdb.ui.paging.main.tvshow.*
import com.sample.android.tmdb.ui.paging.search.movie.SearchMovieActivity
import com.sample.android.tmdb.ui.paging.search.tvshow.SearchTVShowActivity
import com.sample.android.tmdb.ui.person.PersonActivity
import com.sample.android.tmdb.ui.start.StartActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [InAppUpdateModule::class])
    internal abstract fun startActivity(): StartActivity

    @ContributesAndroidInjector(modules = [FeedModule::class])
    internal abstract fun feedActivity(): FeedActivity

    @ContributesAndroidInjector(modules = [TrendingMoviesModule::class])
    internal abstract fun trendingMoviesActivity(): TrendingMoviesActivity

    @ContributesAndroidInjector(modules = [NowPlayingMoviesModule::class])
    internal abstract fun nowPlayingMoviesActivity(): NowPlayingMoviesActivity

    @ContributesAndroidInjector(modules = [PopularMoviesModule::class])
    internal abstract fun popularMoviesActivity(): PopularMoviesActivity

    @ContributesAndroidInjector(modules = [UpcomingMoviesModule::class])
    internal abstract fun upcomingMoviesActivity(): UpcomingMoviesActivity

    @ContributesAndroidInjector(modules = [HighRateMoviesModule::class])
    internal abstract fun highRateMoviesActivity(): HighRateMoviesActivity

    @ContributesAndroidInjector(modules = [TrendingTVShowModule::class])
    internal abstract fun trendingTVShowActivity(): TrendingTVShowActivity

    @ContributesAndroidInjector(modules = [AiringTodayTVShowModule::class])
    internal abstract fun airingTodayTVShowActivity(): AiringTodayTVShowActivity

    @ContributesAndroidInjector(modules = [PopularTVShowModule::class])
    internal abstract fun popularTVShowActivity(): PopularTVShowActivity

    @ContributesAndroidInjector(modules = [OnTheAirTVShowModule::class])
    internal abstract fun onTheAirTVShowActivity(): OnTheAirTVShowActivity

    @ContributesAndroidInjector(modules = [HighRateTVShowModule::class])
    internal abstract fun highRateTVShowActivity(): HighRateTVShowActivity

    @ContributesAndroidInjector(modules = [SearchMovieModule::class])
    internal abstract fun searchMovieActivity(): SearchMovieActivity

    @ContributesAndroidInjector(modules = [SearchTVShowModule::class])
    internal abstract fun searchTVShowActivity(): SearchTVShowActivity

    @ContributesAndroidInjector(modules = [DetailMovieModule::class])
    internal abstract fun detailMovieActivity(): DetailMovieActivity

    @ContributesAndroidInjector(modules = [DetailTVShowModule::class])
    internal abstract fun detailTVShowActivity(): DetailTVShowActivity

    @ContributesAndroidInjector(modules = [PersonModule::class])
    internal abstract fun personActivity(): PersonActivity
}
package com.sample.android.tmdb.di

import com.sample.android.tmdb.di.movie.DetailMovieModule
import com.sample.android.tmdb.di.movie.DiscoverMoviesModule
import com.sample.android.tmdb.di.movie.HighRateMoviesModule
import com.sample.android.tmdb.di.movie.NowPlayingMoviesModule
import com.sample.android.tmdb.di.movie.PopularMoviesModule
import com.sample.android.tmdb.di.movie.SearchMovieModule
import com.sample.android.tmdb.di.movie.TrendingMoviesModule
import com.sample.android.tmdb.di.movie.UpcomingMoviesModule
import com.sample.android.tmdb.di.tvshow.AiringTodayTVShowModule
import com.sample.android.tmdb.di.tvshow.DetailTVShowModule
import com.sample.android.tmdb.di.tvshow.DiscoverTVShowModule
import com.sample.android.tmdb.di.tvshow.HighRateTVShowModule
import com.sample.android.tmdb.di.tvshow.OnTheAirTVShowModule
import com.sample.android.tmdb.di.tvshow.PopularTVShowModule
import com.sample.android.tmdb.di.tvshow.SearchTVShowModule
import com.sample.android.tmdb.di.tvshow.TrendingTVShowModule
import com.sample.android.tmdb.ui.detail.movie.DetailMovieActivity
import com.sample.android.tmdb.ui.detail.tvshow.DetailTVShowActivity
import com.sample.android.tmdb.ui.feed.FeedActivity
import com.sample.android.tmdb.ui.paging.main.movie.DiscoverMoviesActivity
import com.sample.android.tmdb.ui.paging.main.movie.HighRateMoviesActivity
import com.sample.android.tmdb.ui.paging.main.movie.NowPlayingMoviesActivity
import com.sample.android.tmdb.ui.paging.main.movie.PopularMoviesActivity
import com.sample.android.tmdb.ui.paging.main.movie.TrendingMoviesActivity
import com.sample.android.tmdb.ui.paging.main.movie.UpcomingMoviesActivity
import com.sample.android.tmdb.ui.paging.main.tvshow.AiringTodayTVShowActivity
import com.sample.android.tmdb.ui.paging.main.tvshow.DiscoverTVShowsActivity
import com.sample.android.tmdb.ui.paging.main.tvshow.HighRateTVShowActivity
import com.sample.android.tmdb.ui.paging.main.tvshow.OnTheAirTVShowActivity
import com.sample.android.tmdb.ui.paging.main.tvshow.PopularTVShowActivity
import com.sample.android.tmdb.ui.paging.main.tvshow.TrendingTVShowActivity
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

    @ContributesAndroidInjector(modules = [DiscoverMoviesModule::class])
    internal abstract fun discoverMoviesActivity(): DiscoverMoviesActivity

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

    @ContributesAndroidInjector(modules = [DiscoverTVShowModule::class])
    internal abstract fun discoverTVShowActivity(): DiscoverTVShowsActivity

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
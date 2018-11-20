package com.sample.android.tmdb.di

import android.app.Application

import com.sample.android.tmdb.MovieApp

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(modules = [ActivityBindingModule::class,
    AndroidSupportInjectionModule::class,
    NetworkModule::class])
interface AppComponent : AndroidInjector<MovieApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}

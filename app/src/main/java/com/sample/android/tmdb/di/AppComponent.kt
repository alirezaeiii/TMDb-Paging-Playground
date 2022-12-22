package com.sample.android.tmdb.di

import com.sample.android.tmdb.Application

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(
    modules = [ActivityBindingModule::class,
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ContextModule::class,
        AppUtilsModule::class,
        DispatcherModule::class]
)
interface AppComponent : AndroidInjector<Application> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: android.app.Application): Builder

        fun build(): AppComponent
    }
}

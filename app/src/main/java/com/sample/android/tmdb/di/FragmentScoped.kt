package com.sample.android.tmdb.di

import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS,
        AnnotationTarget.FILE,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER)
annotation class FragmentScoped
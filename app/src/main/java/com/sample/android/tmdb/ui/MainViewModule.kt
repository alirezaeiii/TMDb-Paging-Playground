package com.sample.android.tmdb.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.sample.android.tmdb.NavType
import com.sample.android.tmdb.R
import timber.log.Timber

class MainViewModule(app: Application) : AndroidViewModel(app) {

    private val _headline = MutableLiveData<String>()
    val headline: LiveData<String>
        get() = _headline

    private val _currentType = MutableLiveData<NavType>()
    val currentType: LiveData<NavType>
    get() = _currentType

    private val context = app

    init {
        Timber.d("View model created")
        _headline.value = context.getString(R.string.menu_movies)
        _currentType.value = NavType.MOVIES
    }

    fun setHeadline(titleId: Int) {
        this._headline.value = context.getString(titleId)
    }

    fun setNavType(navType: NavType) {
        _currentType.value = navType
    }
}
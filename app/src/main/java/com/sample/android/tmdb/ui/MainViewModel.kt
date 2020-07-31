package com.sample.android.tmdb.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.android.tmdb.R
import com.sample.android.tmdb.util.NavType

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val _headline = MutableLiveData<String>()
    val headline: LiveData<String>
        get() = _headline

    private val _currentType = MutableLiveData<NavType>()
    val currentType: LiveData<NavType>
        get() = _currentType

    private val context = app

    init {
        _headline.value = context.getString(R.string.menu_movies)
        _currentType.value = NavType.MOVIES
    }

    fun setHeadlineAndNavType(titleId: Int, navType: NavType) {
        _headline.value = context.getString(titleId)
        _currentType.value = navType
    }
}
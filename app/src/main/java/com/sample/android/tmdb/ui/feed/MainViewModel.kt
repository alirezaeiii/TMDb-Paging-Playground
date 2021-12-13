package com.sample.android.tmdb.ui.feed

import android.app.Application
import android.os.Parcelable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.android.tmdb.R
import kotlinx.android.parcel.Parcelize

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val _headline = MutableLiveData<String>()
    val headline: LiveData<String>
        get() = _headline

    private val _currentType = MutableLiveData<NavType>()
    val currentType: LiveData<NavType>
        get() = _currentType

    private val context = app

    init {
        setType(R.string.menu_movies, NavType.MOVIES)
    }

    fun setType(titleId: Int, navType: NavType) {
        _headline.value = context.getString(titleId)
        _currentType.value = navType
    }
}

@Parcelize
enum class NavType : Parcelable {
    MOVIES,
    TV_SERIES
}
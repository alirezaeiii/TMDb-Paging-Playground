package com.sample.android.tmdb.ui.feed

import android.app.Application
import android.os.Parcelable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import com.sample.android.tmdb.R
import kotlinx.android.parcel.Parcelize

class MainViewModel(
    app: Application,
    private val savedStateHandle: SavedStateHandle
) : AndroidViewModel(app) {

    private val _headline = savedStateHandle.getLiveData<String>(HEAD_LINE)
    val headline: LiveData<String>
        get() = _headline

    private val _currentType = savedStateHandle.getLiveData<NavType>(NAV_TYPE)
    val currentType: LiveData<NavType>
        get() = _currentType

    private val context = app

    init {
        setType(R.string.menu_movies, NavType.MOVIES)
    }

    fun setType(titleId: Int, navType: NavType) {
        savedStateHandle[HEAD_LINE] = context.getString(titleId)
        savedStateHandle[NAV_TYPE] = navType

    }

    companion object {
        private const val HEAD_LINE = "headline"
        private const val NAV_TYPE = "navType"
    }
}

@Parcelize
enum class NavType : Parcelable {
    MOVIES,
    TV_SERIES
}
package com.sample.android.tmdb.ui.feed

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sample.android.tmdb.R
import kotlinx.android.parcel.Parcelize

class MainViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _headline = savedStateHandle.getLiveData<Int>(HEAD_LINE)
    val headline: LiveData<Int>
        get() = _headline

    private val _currentType = savedStateHandle.getLiveData<NavType>(NAV_TYPE)
    val currentType: LiveData<NavType>
        get() = _currentType

    init {
        setType(
            savedStateHandle[HEAD_LINE] ?: R.string.menu_movies,
            savedStateHandle[NAV_TYPE] ?: NavType.MOVIES
        )
    }

    fun setType(titleId: Int, navType: NavType) {
        savedStateHandle[HEAD_LINE] = titleId
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
package com.sample.android.tmdb.util

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.detail.DetailActivity
import com.sample.android.tmdb.ui.feed.NavType

val Context.layoutInflater: LayoutInflater get() = LayoutInflater.from(this)

@Suppress("DEPRECATION")
fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            //for other device how are able to connect with Ethernet
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            //for check internet over Bluetooth
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
            else -> false
        }
    } else {
        val nwInfo = connectivityManager.activeNetworkInfo ?: return false
        return nwInfo.isConnected
    }
}

fun Context.startDetailActivity(tmdbItem: TmdbItem, navType: NavType?) {
    val intent = Intent(this, DetailActivity::class.java).apply {
        putExtras(Bundle().apply {
            putParcelable(Constants.EXTRA_TMDB_ITEM, tmdbItem)
            putParcelable(Constants.EXTRA_NAV_TYPE, navType)
        })
    }
    startActivity(intent)

}
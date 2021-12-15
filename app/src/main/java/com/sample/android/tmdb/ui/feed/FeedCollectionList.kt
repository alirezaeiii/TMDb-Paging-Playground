package com.sample.android.tmdb.ui.feed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.paging.main.MainActivity
import com.sample.android.tmdb.ui.paging.main.SortType
import com.sample.android.tmdb.util.Constants

@Composable
fun <T : TmdbItem> FeedCollectionList(
    context: Context,
    navType: NavType,
    collection: List<List<T>>,
    onFeedClick: OnFeedClickListener
) {
    LazyColumn(modifier = Modifier.padding(bottom = 48.dp)) {

        itemsIndexed(collection) { index, feedCollection ->
            val pair = when (index) {
                0 -> Pair("Popular", SortType.MOST_POPULAR)
                1 -> Pair("Upcoming", SortType.UPCOMING)
                2 -> Pair("Top Rated", SortType.HIGHEST_RATED)
                else -> throw RuntimeException("Invalid index for collection")
            }
            FeedCollection(
                feedCollection = feedCollection,
                name = pair.first,
                onMoreClick = {
                    val intent = Intent(context, MainActivity::class.java).apply {
                        putExtras(Bundle().apply {
                            putParcelable(Constants.EXTRA_SORT_TYPE, pair.second)
                            putParcelable(Constants.EXTRA_NAV_TYPE, navType)
                        })
                    }
                    context.startActivity(intent)
                },
                onFeedClick = onFeedClick
            )
        }
    }
}
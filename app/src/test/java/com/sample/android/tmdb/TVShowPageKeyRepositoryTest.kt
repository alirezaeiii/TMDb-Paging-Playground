package com.sample.android.tmdb

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagedList
import com.google.common.collect.Lists
import com.sample.android.tmdb.domain.ItemWrapper
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.paging.tvshow.TVShowsPageKeyRepository
import com.sample.android.tmdb.ui.item.SortType.*
import com.sample.android.tmdb.util.isNetworkAvailable
import io.mockk.every
import io.mockk.mockkStatic
import io.reactivex.Observable
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyInt
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executor

@RunWith(MockitoJUnitRunner::class)
class TVShowPageKeyRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var api: TVShowApi

    @Mock
    private lateinit var context: Context

    private val networkExecutor = Executor { command -> command.run() }

    private lateinit var tvShow: TVShow

    @Before
    fun setup() {
        mockkStatic("com.sample.android.tmdb.util.ContextExtKt")
        every {
            context.isNetworkAvailable()
        } returns true
        tvShow = TVShow(1, "overview", "date",
                null, null, "title", 6.5)
    }

    @Test
    fun loadMostPopularTVShows() {
        val repository = TVShowsPageKeyRepository(api, MOST_POPULAR, networkExecutor, context)
        `when`(api.popularItems(anyInt())).thenReturn(Observable.just(ItemWrapper(Lists.newArrayList(tvShow))))

        with(getObserver(repository).value) {
            assertThat(this, `is`(notNullValue()))
            assertThat(this?.size, `is`(1))
        }
    }

    @Test
    fun loadHighRatedTVShows() {
        val repository = TVShowsPageKeyRepository(api, HIGHEST_RATED, networkExecutor, context)
        `when`(api.topRatedItems(anyInt())).thenReturn(Observable.just(ItemWrapper(Lists.newArrayList(tvShow))))

        with(getObserver(repository).value) {
            assertThat(this, `is`(notNullValue()))
            assertThat(this?.size, `is`(1))
        }
    }

    @Test
    fun loadUpcomingTVShows() {
        val repository = TVShowsPageKeyRepository(api, UPCOMING, networkExecutor, context)
        `when`(api.latestItems(anyInt())).thenReturn(Observable.just(ItemWrapper(Lists.newArrayList(tvShow))))

        with(getObserver(repository).value) {
            assertThat(this, `is`(notNullValue()))
            assertThat(this?.size, `is`(1))
        }
    }

    private fun getObserver(repository: TVShowsPageKeyRepository): LoggingObserver<PagedList<TVShow>> {
        val listing = repository.getItems()
        val observer = LoggingObserver<PagedList<TVShow>>()
        listing.pagedList.observeForever(observer)
        return observer
    }
}
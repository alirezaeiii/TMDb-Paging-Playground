package com.sample.android.tmdb.paging.movie

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagedList
import com.google.common.collect.Lists
import com.sample.android.tmdb.LoggingObserver
import com.sample.android.tmdb.data.response.ItemWrapper
import com.sample.android.tmdb.data.response.NetworkMovie
import com.sample.android.tmdb.domain.model.Movie
import com.sample.android.tmdb.data.network.MovieApi
import com.sample.android.tmdb.domain.model.SortType.*
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
class MoviePageKeyRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var api: MovieApi

    @Mock
    private lateinit var context: Context

    private val networkExecutor = Executor { command -> command.run() }

    private lateinit var movie: NetworkMovie

    @Before
    fun setup() {
        mockkStatic("com.sample.android.tmdb.util.ContextExtKt")
        every {
            context.isNetworkAvailable()
        } returns true
        movie = NetworkMovie(1, "overview", "date",
                null, null, "title", 6.5)
    }

    @Test
    fun loadTrendingMovies() {
        val repository = MoviePageKeyRepository(api, TRENDING, networkExecutor, context)
        `when`(api.trendingMovies(anyInt())).thenReturn(Observable.just(ItemWrapper(Lists.newArrayList(movie))))

        with(getObserver(repository).value) {
            assertThat(this, `is`(notNullValue()))
            assertThat(this?.size, `is`(1))
        }
    }

    @Test
    fun loadNowPlayingMovies() {
        val repository = MoviePageKeyRepository(api, NOW_PLAYING, networkExecutor, context)
        `when`(api.nowPlayingMovies(anyInt())).thenReturn(Observable.just(ItemWrapper(Lists.newArrayList(movie))))

        with(getObserver(repository).value) {
            assertThat(this, `is`(notNullValue()))
            assertThat(this?.size, `is`(1))
        }
    }

    @Test
    fun loadMostPopularMovies() {
        val repository = MoviePageKeyRepository(api, MOST_POPULAR, networkExecutor, context)
        `when`(api.popularMovies(anyInt())).thenReturn(Observable.just(ItemWrapper(Lists.newArrayList(movie))))

        with(getObserver(repository).value) {
            assertThat(this, `is`(notNullValue()))
            assertThat(this?.size, `is`(1))
        }
    }

    @Test
    fun loadHighRatedMovies() {
        val repository = MoviePageKeyRepository(api, HIGHEST_RATED, networkExecutor, context)
        `when`(api.topRatedMovies(anyInt())).thenReturn(Observable.just(ItemWrapper(Lists.newArrayList(movie))))

        with(getObserver(repository).value) {
            assertThat(this, `is`(notNullValue()))
            assertThat(this?.size, `is`(1))
        }
    }

    @Test
    fun loadUpcomingMovies() {
        val repository = MoviePageKeyRepository(api, UPCOMING, networkExecutor, context)
        `when`(api.upcomingMovies(anyInt())).thenReturn(Observable.just(ItemWrapper(Lists.newArrayList(movie))))

        with(getObserver(repository).value) {
            assertThat(this, `is`(notNullValue()))
            assertThat(this?.size, `is`(1))
        }
    }

    private fun getObserver(repository: MoviePageKeyRepository): LoggingObserver<PagedList<Movie>> {
        val listing = repository.getItems()
        val observer = LoggingObserver<PagedList<Movie>>()
        listing.pagedList.observeForever(observer)
        return observer
    }
}
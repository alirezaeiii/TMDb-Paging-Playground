package com.sample.android.tmdb

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagedList
import com.google.common.collect.Lists
import com.sample.android.tmdb.domain.ItemWrapper
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.paging.search.movie.SearchMoviePageKeyRepository
import com.sample.android.tmdb.util.isNetworkAvailable
import io.mockk.every
import io.mockk.mockkStatic
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response
import retrofit2.mock.Calls
import java.util.concurrent.Executor

@RunWith(MockitoJUnitRunner::class)
class SearchMoviePageKeyRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var api: MovieApi

    @Mock
    private lateinit var context: Context

    private val networkExecutor = Executor { command -> command.run() }

    private lateinit var mockCall: Call<ItemWrapper<Movie>>

    @Before
    fun setup() {
        mockkStatic("com.sample.android.tmdb.util.ContextExtKt")
        every {
            context.isNetworkAvailable()
        } returns true
        val movie = Movie(1, "overview", "date",
                null, null, "title", 6.5)

        mockCall = Calls.response(Response.success(ItemWrapper(Lists.newArrayList(movie))))
    }

    @Test
    fun searchMovies() {
        val repository = SearchMoviePageKeyRepository(api, "", context)
        `when`(api.searchItems(anyInt(), anyString())).thenReturn(mockCall)

        val listing = repository.getItems(networkExecutor)
        val observer = LoggingObserver<PagedList<Movie>>()
        listing.pagedList.observeForever(observer)

        with(observer.value) {
            assertThat(this, `is`(notNullValue()))
            assertThat(this?.size, `is`(1))
        }
    }
}
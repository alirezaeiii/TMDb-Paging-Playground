package com.sample.android.tmdb

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import com.google.common.collect.Lists
import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.repository.bypage.movie.MoviePageKeyRepository
import com.sample.android.tmdb.usecase.ItemUseCase
import com.sample.android.tmdb.util.SortType
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
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.mock.Calls
import java.util.concurrent.Executor

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var itemApi: ItemApi
    private lateinit var useCase: ItemUseCase
    private val networkExecutor = Executor { command -> command.run() }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = ItemUseCase(itemApi)
    }

    @Test
    fun loadMostPopularMovies() {
        val repository = MoviePageKeyRepository(useCase, SortType.MOST_POPULAR)
        val movie = Movie(1, "overview", "date",
                null, null, "title", 6.5)

        val mockCall = Calls.response(Response.success(
                ItemApi.MovieWrapper(Lists.newArrayList(movie))))
        `when`(itemApi.popularMovies(anyInt())).thenReturn(mockCall)

        val listing = repository.getItems("", networkExecutor)
        val observer = LoggingObserver<PagedList<Movie>>()
        listing.pagedList.observeForever(observer)

        with(observer.value) {
            assertThat(this, `is`(notNullValue()))
            assertThat(this?.size, `is`(1))
        }
    }

    /**
     * simple observer that logs the latest value it receives
     */
    private class LoggingObserver<T> : Observer<T> {
        var value: T? = null
        override fun onChanged(t: T?) {
            this.value = t
        }
    }
}
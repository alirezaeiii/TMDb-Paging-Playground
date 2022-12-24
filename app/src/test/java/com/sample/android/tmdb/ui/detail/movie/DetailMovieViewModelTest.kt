package com.sample.android.tmdb.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.android.tmdb.TestRxJavaRule
import com.sample.android.tmdb.domain.MovieDetailRepository
import com.sample.android.tmdb.domain.model.Cast
import com.sample.android.tmdb.domain.model.CreditWrapper
import com.sample.android.tmdb.domain.model.Movie
import com.sample.android.tmdb.domain.model.Video
import io.reactivex.Single
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var rxJavaRule: TestRule = TestRxJavaRule()

    @Mock
    private lateinit var repository: MovieDetailRepository

    private lateinit var movie: Movie

    @Before
    fun setup() {
        movie = Movie(1, "", null, null, null, "", 1.1)
    }

    @Test
    fun loadTrailersAndCredits() {
        val trailers = listOf(Video("id", "", "", "", ""))
        val creditWrapper = CreditWrapper(Single.just(listOf(Cast("", "", null, 1))),
            Single.just(listOf()))
        `when`(repository.getMovieTrailers(anyInt())).thenReturn(Single.just(trailers))
        `when`(repository.getMovieCredit(anyInt())).thenReturn(creditWrapper)

        val viewModel = DetailMovieViewModel(repository, movie)

        viewModel.liveData.value?.let {
            assertTrue(it.videos.size == 1)
            assertTrue(it.cast.size == 1)
            assertTrue(it.crew.isEmpty())
            assertTrue(it.videos[0].id == "id")
            assertTrue(it.cast[0].id == 1)
        }
    }

    @Test
    fun errorLoadTrailers() {
        val creditWrapper = CreditWrapper(Single.just(listOf(Cast("", "", null, 1))),
            Single.just(listOf()))
        `when`(repository.getMovieTrailers(anyInt())).thenReturn(Single.error(Exception()))
        `when`(repository.getMovieCredit(anyInt())).thenReturn(creditWrapper)

        val viewModel = DetailMovieViewModel(repository, movie)

        with(viewModel.liveData.value) {
            assertThat(this, `is`(nullValue()))
        }
    }

    @Test
    fun errorLoadCredits() {
        val trailers = listOf(Video("id", "", "", "", ""))
        val creditWrapper = CreditWrapper(Single.just(listOf(Cast("", "", null, 1))),
            Single.error(Exception()))
        `when`(repository.getMovieTrailers(anyInt())).thenReturn(Single.just(trailers))
        `when`(repository.getMovieCredit(anyInt())).thenReturn(creditWrapper)

        val viewModel = DetailMovieViewModel(repository, movie)

        with(viewModel.liveData.value) {
            assertThat(this, `is`(nullValue()))
        }
    }
}
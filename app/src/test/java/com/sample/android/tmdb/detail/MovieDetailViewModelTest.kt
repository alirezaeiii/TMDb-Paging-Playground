package com.sample.android.tmdb.detail

import android.app.Application
import com.google.common.collect.Lists
import com.sample.android.tmdb.api.MovieApi
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.ui.detail.MovieDetailViewModel
import com.sample.android.tmdb.vo.Cast
import com.sample.android.tmdb.vo.Movie
import com.sample.android.tmdb.vo.Video
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.rules.TestRule
import org.junit.Rule


@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var context: Application
    @Mock
    private lateinit var movieApi: MovieApi
    private lateinit var dataSource: MoviesRemoteDataSource
    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var movie: Movie

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)

        dataSource = MoviesRemoteDataSource(movieApi)
        viewModel = MovieDetailViewModel(context, dataSource)

        movie = Movie("id", "overview", "date",
                null, null, "title", 6.5)
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun loadMovieTrailer() {
        val video = Video("id", "name", "site",
                "videoId", 20, "type")

        val observableResponse =
                Observable.just(MovieApi.VideoWrapper(Lists.newArrayList(video)))
        `when`(movieApi.trailers(anyString())).thenReturn(observableResponse)


        with(viewModel) {
            assertFalse(isTrailersVisible.get())

            showTrailers(movie)

            assertTrue(isTrailersVisible.get())
            assertFalse(trailers.isEmpty())
            assertTrue(trailers.size == 1)
        }
    }

    @Test
    fun loadMovieCast() {
        val cast = Cast("character", "name", null, 1)

        val observableResponse =
                Observable.just(MovieApi.CastWrapper(Lists.newArrayList(cast)))
        `when`(movieApi.cast(anyString())).thenReturn(observableResponse)

        with(viewModel) {
            assertFalse(isCastVisible.get())

            showCast(movie)

            assertTrue(isCastVisible.get())
            with(this.cast.value!!) {
                assertFalse(isEmpty())
                assertTrue(size == 1)
            }
        }
    }
}
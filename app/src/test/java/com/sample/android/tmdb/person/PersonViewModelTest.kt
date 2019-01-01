package com.sample.android.tmdb.person

import android.app.Application
import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.android.tmdb.api.MovieApi
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.ui.person.PersonViewModel
import com.sample.android.tmdb.vo.Person
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.*
import org.junit.Assert.assertTrue
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PersonViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var context: Application
    @Mock
    private lateinit var movieApi: MovieApi
    private lateinit var dataSource: MoviesRemoteDataSource
    private lateinit var viewModel: PersonViewModel

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)

        dataSource = MoviesRemoteDataSource(movieApi)
        viewModel = PersonViewModel(context, dataSource)
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun loadPerson() {
        val personId = 12
        val knownAs1 = "Ali"
        val knownAs2 = "Harry"
        val person = Person(null ,null, personId, arrayOf(knownAs1, knownAs2),
                "biography", "place")

        val observableResponse = Observable.just(person)
        `when`(movieApi.person(anyInt())).thenReturn(observableResponse)

        with(viewModel) {
            Assert.assertFalse(isVisible.get())

            showPerson(personId)

            assertTrue(isVisible.get())
            assertTrue(this.person.value?.id == personId)
            with(this.knownAs.value!!) {
                assertTrue(size == 2)
                assertTrue(this[0] == knownAs1)
                assertTrue(this[1] == knownAs2)
            }
        }
    }
}
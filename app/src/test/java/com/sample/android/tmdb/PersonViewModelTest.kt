package com.sample.android.tmdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.domain.Person
import com.sample.android.tmdb.ui.person.PersonViewModel
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
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
    private lateinit var api: TmdbApi

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)
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
        `when`(api.person(anyInt())).thenReturn(observableResponse)

        val viewModel = PersonViewModel(api, anyInt())

        with(viewModel) {
            assertTrue(this.person.value?.id == personId)
            with(this.person.value?.alsoKnowAs!!) {
                assertTrue(size == 2)
                assertTrue(this[0] == knownAs1)
                assertTrue(this[1] == knownAs2)
            }
        }
    }

    @Test
    fun errorLoadPerson() {
        val observableResponse = Observable.error<Person>(Exception())
        `when`(api.person(anyInt())).thenReturn(observableResponse)

        val viewModel = PersonViewModel(api, anyInt())

        with(viewModel) {
            Assert.assertThat(person.value, `is`(nullValue()))
        }
    }
}
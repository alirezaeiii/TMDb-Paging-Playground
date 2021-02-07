package com.sample.android.tmdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.android.tmdb.domain.Person
import com.sample.android.tmdb.network.PersonApi
import com.sample.android.tmdb.ui.person.PersonViewModel
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.assertTrue

@RunWith(MockitoJUnitRunner::class)
class PersonViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var api: PersonApi

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
        val person = Person(null, null, personId, arrayOf(knownAs1, knownAs2),
                "biography", "place")

        val observableResponse = Observable.just(person)
        `when`(api.person(anyInt())).thenReturn(observableResponse)

        val viewModel = PersonViewModel(api, anyInt())

        viewModel.liveData.value?.let {
            assertTrue(it.id == personId)
            assertTrue(it.alsoKnowAs.size == 2)
            assertTrue(it.alsoKnowAs[0] == knownAs1)
            assertTrue(it.alsoKnowAs[1] == knownAs2)
        }
    }

    @Test
    fun errorLoadPerson() {
        `when`(api.person(anyInt())).thenReturn(Observable.error(Exception()))

        val viewModel = PersonViewModel(api, anyInt())

        with(viewModel) {
            assertThat(liveData.value, `is`(nullValue()))
        }
    }
}
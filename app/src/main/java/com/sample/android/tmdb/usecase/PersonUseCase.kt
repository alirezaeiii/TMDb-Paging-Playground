package com.sample.android.tmdb.usecase

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.Person
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonUseCase @Inject constructor(
        private val itemApi: ItemApi) : BaseUseCase() {

    fun getPerson(id: Int): Observable<Person> = composeObservable { itemApi.person(id) }
}
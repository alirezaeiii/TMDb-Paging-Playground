package com.sample.android.tmdb.network

import com.sample.android.tmdb.domain.CreditWrapper
import com.sample.android.tmdb.domain.ItemWrapper
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.domain.VideoWrapper
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("3/discover/movie?language=en&sort_by=popularity.desc")
    fun popularItems(@Query("page") page: Int): Call<ItemWrapper<Movie>>

    @GET("3/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc")
    fun topRatedItems(@Query("page") page: Int): Call<ItemWrapper<Movie>>

    @GET("3/movie/upcoming?language=en")
    fun latestItems(@Query("page") page: Int): Call<ItemWrapper<Movie>>

    @GET("3/search/movie?language=en")
    fun searchItems(@Query("page") page: Int, @Query("query") query: String): Call<ItemWrapper<Movie>>

    @GET("3/movie/{movieId}/videos")
    fun movieTrailers(@Path("movieId") movieId: Int): Single<VideoWrapper>

    @GET("3/movie/{movieId}/credits")
    fun movieCredit(@Path("movieId") movieId: Int): Single<CreditWrapper>
}
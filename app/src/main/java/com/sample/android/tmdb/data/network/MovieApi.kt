package com.sample.android.tmdb.data.network

import com.sample.android.tmdb.data.response.ItemWrapper
import com.sample.android.tmdb.data.response.NetworkCreditWrapper
import com.sample.android.tmdb.data.response.NetworkMovie
import com.sample.android.tmdb.data.response.VideoWrapper
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("3/trending/movie/day")
    suspend fun trendingMovies(): ItemWrapper<NetworkMovie>

    @GET("3/movie/now_playing")
    suspend fun nowPlayingMovies(): ItemWrapper<NetworkMovie>

    @GET("3/movie/popular")
    suspend fun popularMovies(): ItemWrapper<NetworkMovie>

    @GET("3/movie/upcoming")
    suspend fun upcomingMovies(): ItemWrapper<NetworkMovie>

    @GET("3/movie/top_rated")
    suspend fun topRatedMovies(): ItemWrapper<NetworkMovie>

    @GET("3/trending/movie/day")
    fun trendingMovies(@Query("page") page: Int): Observable<ItemWrapper<NetworkMovie>>

    @GET("3/movie/now_playing")
    fun nowPlayingMovies(@Query("page") page: Int): Observable<ItemWrapper<NetworkMovie>>

    @GET("3/movie/popular")
    fun popularMovies(@Query("page") page: Int): Observable<ItemWrapper<NetworkMovie>>

    @GET("3/movie/top_rated")
    fun topRatedMovies(@Query("page") page: Int): Observable<ItemWrapper<NetworkMovie>>

    @GET("3/movie/upcoming")
    fun upcomingMovies(@Query("page") page: Int): Observable<ItemWrapper<NetworkMovie>>

    @GET("3/search/movie")
    fun searchItems(@Query("page") page: Int, @Query("query") query: String): Observable<ItemWrapper<NetworkMovie>>

    @GET("3/movie/{movieId}/videos")
    fun movieTrailers(@Path("movieId") movieId: Int): Single<VideoWrapper>

    @GET("3/movie/{movieId}/credits")
    fun movieCredit(@Path("movieId") movieId: Int): Single<NetworkCreditWrapper>
}
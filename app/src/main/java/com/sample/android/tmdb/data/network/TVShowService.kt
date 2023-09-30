package com.sample.android.tmdb.data.network

import com.sample.android.tmdb.data.response.TMDbWrapper
import com.sample.android.tmdb.data.response.NetworkCreditWrapper
import com.sample.android.tmdb.data.response.NetworkTVShow
import com.sample.android.tmdb.data.response.VideoWrapper
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVShowService {

    @GET("3/trending/tv/day")
    suspend fun trendingTVSeries(): TMDbWrapper<NetworkTVShow>

    @GET("3/tv/airing_today")
    suspend fun airingTodayTVSeries(): TMDbWrapper<NetworkTVShow>

    @GET("3/tv/popular")
    suspend fun popularTVSeries(): TMDbWrapper<NetworkTVShow>

    @GET("3/tv/on_the_air")
    suspend fun onTheAirTVSeries(): TMDbWrapper<NetworkTVShow>

    @GET("3/tv/top_rated")
    suspend fun topRatedTVSeries(): TMDbWrapper<NetworkTVShow>

    @GET("3/discover/tv")
    suspend fun discoverTVSeries(): TMDbWrapper<NetworkTVShow>

    @GET("3/trending/tv/day")
    fun trendingTVSeries(@Query("page") page: Int): Observable<TMDbWrapper<NetworkTVShow>>

    @GET("3/tv/airing_today")
    fun airingTodayTVSeries(@Query("page") page: Int): Observable<TMDbWrapper<NetworkTVShow>>

    @GET("3/tv/popular")
    fun popularTVSeries(@Query("page") page: Int): Observable<TMDbWrapper<NetworkTVShow>>

    @GET("3/tv/top_rated")
    fun topRatedTVSeries(@Query("page") page: Int): Observable<TMDbWrapper<NetworkTVShow>>

    @GET("3/tv/on_the_air")
    fun onTheAirTVSeries(@Query("page") page: Int): Observable<TMDbWrapper<NetworkTVShow>>

    @GET("3/discover/tv")
    fun discoverTVSeries(@Query("page") page: Int): Observable<TMDbWrapper<NetworkTVShow>>

    @GET("3/search/tv")
    fun searchItems(@Query("page") page: Int, @Query("query") query: String): Observable<TMDbWrapper<NetworkTVShow>>

    @GET("3/tv/{tvId}/videos")
    fun tvTrailers(@Path("tvId") tvId: Int): Single<VideoWrapper>

    @GET("3/tv/{tvId}/credits")
    fun tvCredit(@Path("tvId") tvId: Int): Single<NetworkCreditWrapper>
}
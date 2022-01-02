package com.sample.android.tmdb.network

import com.sample.android.tmdb.domain.CreditWrapper
import com.sample.android.tmdb.domain.ItemWrapper
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.domain.VideoWrapper
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVShowApi {

    @GET("3/trending/tv/day")
    suspend fun trendingTVSeries(): ItemWrapper<TVShow>

    @GET("3/tv/popular")
    suspend fun popularTVSeries(): ItemWrapper<TVShow>

    @GET("3/tv/on_the_air")
    suspend fun latestTVSeries(): ItemWrapper<TVShow>

    @GET("3/tv/top_rated")
    suspend fun topRatedTVSeries(): ItemWrapper<TVShow>

    @GET("3/trending/tv/day")
    fun trendingTVSeries(@Query("page") page: Int): Observable<ItemWrapper<TVShow>>

    @GET("3/tv/popular?language=en")
    fun popularTVSeries(@Query("page") page: Int): Observable<ItemWrapper<TVShow>>

    @GET("3/tv/top_rated?language=en")
    fun topRatedTVSeries(@Query("page") page: Int): Observable<ItemWrapper<TVShow>>

    @GET("3/tv/on_the_air?language=en")
    fun latestTVSeries(@Query("page") page: Int): Observable<ItemWrapper<TVShow>>

    @GET("3/search/tv?language=en")
    fun searchItems(@Query("page") page: Int, @Query("query") query: String): Observable<ItemWrapper<TVShow>>

    @GET("3/tv/{tvId}/videos")
    fun tvTrailers(@Path("tvId") tvId: Int): Single<VideoWrapper>

    @GET("3/tv/{tvId}/credits")
    fun tvCredit(@Path("tvId") tvId: Int): Single<CreditWrapper>
}
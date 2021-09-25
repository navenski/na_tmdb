package com.navektest.data.trending.remote

import com.navektest.data.common.remote.response.MediaResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface TrendingApi {

    @GET("${TRENDING}movie/week")
    suspend fun getMovieTrendingWeek(@Query("api_key") apiKey: String): MediaResponse

    @GET("${TRENDING}movie/week")
    suspend fun getTvTrendingWeek(@Query("api_key") apiKey: String): MediaResponse

    companion object {
        const val TRENDING = "3/trending/"
    }
}
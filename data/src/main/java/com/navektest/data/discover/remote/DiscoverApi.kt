package com.navektest.data.discover.remote

import com.navektest.data.common.remote.request.RequestSortBy
import com.navektest.data.common.remote.response.MediaResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface DiscoverApi {

    @GET("${DISCOVER}movie")
    suspend fun getMovie(@Query("api_key") apiKey: String,
                         @Query("page") page: Int,
                         @Query("language") language: String? = null,
                         @Query("sort_by") sortBy: String = RequestSortBy.POPULARITY_DESC.value): MediaResponse

    @GET("${DISCOVER}tv")
    suspend fun getTv(@Query("api_key") apiKey: String,
                      @Query("page") page: Int,
                      @Query("language") language: String? = null,
                      @Query("sort_by") sortBy: String = RequestSortBy.POPULARITY_DESC.value): MediaResponse

    companion object {
        const val DISCOVER = "3/discover/"
    }
}
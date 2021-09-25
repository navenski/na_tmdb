package com.navektest.data.search.remote

import com.navektest.data.common.remote.response.MediaResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface SearchApi {
    @GET("${SEARCH}multi")
    suspend fun multiSearch(@Query("api_key") apiKey: String,
                            @Query("query") query: String,
                            @Query("page") page: Int): MediaResponse

    companion object {
        const val SEARCH = "3/search/"
    }
}
package com.navektest.data.search.remote

import com.navektest.data.search.remote.response.SearchMultiResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface SearchApi {
    @GET("${SEARCH}multi")
    suspend fun multiSearch(@Query("api_key") apiKey: String,
                            @Query("query") query: String,
                            @Query("page") page: Int): SearchMultiResponse

    companion object {
        const val SEARCH = "3/search/"
    }
}
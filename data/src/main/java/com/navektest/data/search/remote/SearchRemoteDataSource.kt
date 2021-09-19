package com.navektest.data.search.remote

import com.navektest.data.annotation.ApiKey
import com.navektest.data.search.remote.response.SearchMultiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class SearchRemoteDataSource @Inject constructor(private val searchApi: SearchApi,
                                                          @ApiKey private val apiKey: String) {

    fun multiSearch(query: String, page: Int): Flow<SearchMultiResponse> = flow {
        val result = searchApi.multiSearch(apiKey, query, page)
        emit(result)
    }
}
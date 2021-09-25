package com.navektest.data.search.remote

import com.navektest.data.common.annotation.ApiKey
import com.navektest.data.common.remote.response.MediaResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class SearchRemoteDataSource @Inject constructor(private val searchApi: SearchApi,
                                                          @ApiKey private val apiKey: String) {

    fun multiSearch(query: String, page: Int): Flow<MediaResponse> = flow {
        val result = searchApi.multiSearch(apiKey, query, page)
        emit(result)
    }
}
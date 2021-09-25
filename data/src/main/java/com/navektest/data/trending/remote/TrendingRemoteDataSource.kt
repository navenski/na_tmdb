package com.navektest.data.trending.remote

import com.navektest.data.common.annotation.ApiKey
import com.navektest.data.common.remote.response.MediaResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class TrendingRemoteDataSource @Inject constructor(private val api: TrendingApi,
                                                            @ApiKey private val apiKey: String) {

    fun getMovie(): Flow<MediaResponse> {
        return flow {
            val response = api.getMovieTrendingWeek(apiKey)
            emit(response)
        }
    }

    fun getTv(): Flow<MediaResponse> = flow {
        val response = api.getTvTrendingWeek(apiKey)
        emit(response)
    }

}
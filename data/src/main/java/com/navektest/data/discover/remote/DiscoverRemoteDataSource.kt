package com.navektest.data.discover.remote

import com.navektest.business.model.MediaPage
import com.navektest.business.model.MediaType
import com.navektest.data.common.annotation.ApiKey
import com.navektest.data.common.remote.transformer.MediaPageTransformer
import com.navektest.toolkit.dispatcher.CoroutineDispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DiscoverRemoteDataSource @Inject constructor(@ApiKey private val apiKey: String,
                                                            private val api: DiscoverApi,
                                                            private val dispatcherProvider: CoroutineDispatcherProvider,
                                                            private val transformer: MediaPageTransformer) {

    fun getMovie(page: Int): Flow<MediaPage> = flow {
        val response = api.getMovie(apiKey, page)
        emit(response)
    }.map { transformer.transform(MediaType.MOVIE, it) }
        .flowOn(dispatcherProvider.default())

    fun getTv(page: Int): Flow<MediaPage> = flow {
        val response = api.getTv(apiKey, page)
        emit(response)
    }.map { transformer.transform(MediaType.TV, it) }
        .flowOn(dispatcherProvider.default())

}
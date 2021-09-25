package com.navektest.data.search

import com.navektest.business.model.MediaPage
import com.navektest.business.model.MediaType
import com.navektest.business.model.ResultState
import com.navektest.business.search.SearchMulti
import com.navektest.business.search.SearchRepository
import com.navektest.data.common.remote.transformer.MediaPageTransformer
import com.navektest.data.search.remote.SearchRemoteDataSource
import com.navektest.data.search.transformer.SearchMultiResponseToSearchTransformer
import com.navektest.toolkit.dispatcher.CoroutineDispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onErrorReturn
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(private val coroutineProvider: CoroutineDispatcherProvider,
                                                        private val remoteDataSource: SearchRemoteDataSource,
                                                        private val transformer: MediaPageTransformer) :
    SearchRepository {

    override fun multiSearch(query: String, page: Int): Flow<MediaPage> {
        return remoteDataSource.multiSearch(query, page)
            .map { transformer.transform(MediaType.TV, it) }
            .flowOn(coroutineProvider.default())
    }
}
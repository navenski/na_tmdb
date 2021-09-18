package com.navektest.data.search

import com.navektest.business.model.ResultState
import com.navektest.business.search.SearchMulti
import com.navektest.business.search.SearchRepository
import com.navektest.data.search.remote.SearchRemoteDataSource
import com.navektest.data.search.transformer.SearchMultiResponseToSearchTransformer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(private val remoteDataSource: SearchRemoteDataSource,
                                                        private val responseTransformer: SearchMultiResponseToSearchTransformer) :
    SearchRepository {
    override fun multiSearch(query: String, page: Int): Flow<ResultState<SearchMulti>> {
        return remoteDataSource.multiSearch(query, page)
            .map { ResultState.success(responseTransformer.transform(it)) }
    }
}
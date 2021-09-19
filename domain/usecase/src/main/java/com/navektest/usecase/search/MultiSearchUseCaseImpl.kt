package com.navektest.usecase.search

import com.navektest.business.model.ResultState
import com.navektest.business.search.SearchMulti
import com.navektest.business.search.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

 class MultiSearchUseCaseImpl @Inject constructor(private val searchRepository: SearchRepository) : MultiSearchUseCase {
    override fun execute(query: String, page: Int): Flow<ResultState<SearchMulti>> =
        searchRepository.multiSearch(query, page)
}
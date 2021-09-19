package com.navektest.usecase.search

import com.navektest.business.model.ResultState
import com.navektest.business.search.SearchMulti
import kotlinx.coroutines.flow.Flow

interface MultiSearchUseCase {
    fun execute(query: String, page: Int) : Flow<ResultState<SearchMulti>>
}
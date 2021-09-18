package com.navektest.business.search

import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun multiSearch(query: String, page: Int): Flow<com.navektest.business.model.ResultState<SearchMulti>>
}
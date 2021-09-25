package com.navektest.business.search

import com.navektest.business.model.MediaPage
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun multiSearch(query: String, page: Int): Flow<MediaPage>
}
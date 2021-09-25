package com.navektest.business.discover

import com.navektest.business.model.MediaPage
import kotlinx.coroutines.flow.Flow

interface DiscoverRepository {
    fun getMovie(page: Int): Flow<MediaPage>
    fun getTv(page: Int): Flow<MediaPage>
}
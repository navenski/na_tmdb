package com.navektest.business.trending

import com.navektest.business.model.Media
import com.navektest.business.model.MediaPage
import kotlinx.coroutines.flow.Flow

interface TrendingRepository {
    fun getMovieTrending(): Flow<MediaPage>
    fun getTvTrending(): Flow<MediaPage>
}
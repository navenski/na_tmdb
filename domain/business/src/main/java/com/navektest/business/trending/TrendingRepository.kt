package com.navektest.business.trending

import kotlinx.coroutines.flow.Flow

interface TrendingRepository {
    fun getMovieTrending()
    fun getTvTrending()
    fun getPersonTrending()
}
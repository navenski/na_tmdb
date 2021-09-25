package com.navektest.data.inject

import com.navektest.business.discover.DiscoverRepository
import com.navektest.business.search.SearchRepository
import com.navektest.business.trending.TrendingRepository
import com.navektest.data.discover.DiscoverRepositoryImpl
import com.navektest.data.search.SearchRepositoryImpl
import com.navektest.data.trending.TrendingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AnalyticsModule {

    @Binds
    internal abstract fun bindSearchRepository(
        analyticsServiceImpl: SearchRepositoryImpl
    ): SearchRepository

    @Binds
    internal abstract fun bindTrendingRepository(
        repository: TrendingRepositoryImpl
    ): TrendingRepository

    @Binds
    internal abstract fun bindDiscoverRepository(
        repository: DiscoverRepositoryImpl): DiscoverRepository
}
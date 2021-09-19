package com.navektest.data.inject

import com.navektest.business.search.SearchRepository
import com.navektest.data.search.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AnalyticsModule {

    @Binds
    internal abstract fun bindSearchRepository(
        analyticsServiceImpl: SearchRepositoryImpl
    ): SearchRepository
}
package com.navektest.usecase.inject

import com.navektest.usecase.discover.DiscoverMovieUseCase
import com.navektest.usecase.discover.DiscoverMovieUseCaseImpl
import com.navektest.usecase.discover.DiscoverTvUseCase
import com.navektest.usecase.discover.DiscoverTvUseCaseImpl
import com.navektest.usecase.search.MultiSearchUseCase
import com.navektest.usecase.search.MultiSearchUseCaseImpl
import com.navektest.usecase.trending.GetMovieTrendingUseCase
import com.navektest.usecase.trending.GetMovieTrendingUseCaseImpl
import com.navektest.usecase.trending.GetTvTrendingUseCase
import com.navektest.usecase.trending.GetTvTrendingUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchModule {

    @Binds
    internal abstract fun bindMultiSearchUseCase(
        useCase: MultiSearchUseCaseImpl
    ): MultiSearchUseCase

    @Binds
    internal abstract fun bindGetTvTrendingUseCase(
        useCase: GetTvTrendingUseCaseImpl
    ): GetTvTrendingUseCase

    @Binds
    internal abstract fun bindGetMovieTrendingUseCase(
        useCase: GetMovieTrendingUseCaseImpl
    ): GetMovieTrendingUseCase

    @Binds
    internal abstract fun bindDiscoverMovie(
        useCase: DiscoverMovieUseCaseImpl
    ): DiscoverMovieUseCase

    @Binds
    internal abstract fun bindDiscoverTv(
        useCase: DiscoverTvUseCaseImpl
    ): DiscoverTvUseCase
}
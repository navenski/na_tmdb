package com.navektest.usecase.inject

import com.navektest.usecase.search.MultiSearchUseCase
import com.navektest.usecase.search.MultiSearchUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchModule {

    @Binds
     abstract fun bindMultiSearchUseCase(
        useCase: MultiSearchUseCaseImpl
    ): MultiSearchUseCase
}
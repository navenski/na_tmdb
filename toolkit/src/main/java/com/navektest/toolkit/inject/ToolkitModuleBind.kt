package com.navektest.toolkit.inject

import com.navektest.toolkit.dispatcher.CoroutineDispatcherProvider
import com.navektest.toolkit.dispatcher.CoroutineDispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
abstract class TollkitModule {

    @Binds
    internal abstract fun bindCoroutineDispatcherProvider(
        implem: CoroutineDispatcherProviderImpl
    ): CoroutineDispatcherProvider
}
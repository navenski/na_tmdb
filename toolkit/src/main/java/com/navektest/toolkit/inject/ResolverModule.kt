package com.navektest.toolkit.inject

import com.navektest.toolkit.resolver.ResourceResolver
import com.navektest.toolkit.resolver.ResourceResolverImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ResolverModule {

    @Binds
    internal abstract fun bindResourceResolver(
        implem: ResourceResolverImpl
    ): ResourceResolver
}
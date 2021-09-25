package com.navektest.data.inject

import com.navektest.business.resolver.PicturePathResolver
import com.navektest.data.common.resolver.PicturePathResolverImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ResolverModule {

    @Binds
    internal abstract fun bindPicturePathResolver(
        resolver: PicturePathResolverImpl
    ): PicturePathResolver
}
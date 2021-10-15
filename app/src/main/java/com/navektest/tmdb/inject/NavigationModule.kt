package com.navektest.tmdb.inject

import com.navektest.common_feature.navigation.StartScreenNavigation
import com.navektest.common_feature.navigation.compose.screen.TmdbRouter
import com.navektest.tmdb.navigation.StartScreenNavigationImpl
import com.navektest.tmdb.navigation.TmdbRouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigationModule {
    @Binds
    abstract fun bindStartScreen(
        navigation: StartScreenNavigationImpl
    ): StartScreenNavigation
}

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationViewModelModule {

    @Binds
    abstract fun bindRouter(
        router: TmdbRouterImpl
    ): TmdbRouter
}
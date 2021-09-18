package com.navektest.tmdb.inject

import com.navektest.common_feature.navigation.StartScreenNavigation
import com.navektest.tmdb.navigation.StartScreenNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigationModule {

    @Binds
    abstract fun bindStartScreen(
        navigation: StartScreenNavigationImpl
    ): StartScreenNavigation

}
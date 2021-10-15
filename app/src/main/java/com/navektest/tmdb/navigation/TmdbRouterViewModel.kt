package com.navektest.tmdb.navigation

import com.navektest.common_feature.navigation.compose.screen.Screen
import com.navektest.router_implem.RouterHandler
import com.navektest.router_implem.RouterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TmdbRouterViewModel @Inject constructor(handler: RouterHandler<Screen>) : RouterViewModel<Screen>(handler)
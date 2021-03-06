package com.navektest.tmdb.navigation

import com.navektest.common_feature.navigation.compose.screen.Screen
import com.navektest.common_feature.navigation.compose.screen.TmdbRouter
import com.navektest.router_implem.RouterHandler
import com.navektest.router_implem.RouterTemplate
import javax.inject.Inject

class TmdbRouterImpl @Inject constructor(handler: TmbRouterHandler) : RouterTemplate<Screen>(handler), TmdbRouter
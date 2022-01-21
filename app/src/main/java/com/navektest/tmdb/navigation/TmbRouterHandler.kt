package com.navektest.tmdb.navigation

import com.navektest.common_feature.navigation.compose.screen.Screen
import com.navektest.router_template.RouterHandlerTemplate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TmbRouterHandler @Inject constructor() : RouterHandlerTemplate<Screen>()
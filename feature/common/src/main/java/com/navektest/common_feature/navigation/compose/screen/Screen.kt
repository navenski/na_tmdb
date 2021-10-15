package com.navektest.common_feature.navigation.compose.screen

import com.navektest.router.Route

sealed class Screen(override val route: String)  : Route {
    object Feed : Screen("feed")
}
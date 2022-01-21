package com.navektest.tmdb.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.navektest.common_feature.navigation.compose.screen.Screen

@Composable
fun HomeScreen(){
    val navController = rememberNavController()
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            bottomBar = {
                BottomNavigation {

                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    Screen.getHomeScreens()
                        .forEach { screen ->
                            BottomNavigationItem(icon = {
                                Icon(
                                    painter = painterResource(id = screen.icon),
                                    contentDescription = null // decorative element
                                )
                            },
                                                 label = { Text(stringResource(screen.title)) },
                                                 selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                                 onClick = {
                                                     navController.navigate(screen.route) {
                                                         // Pop up to the start destination of the graph to
                                                         // avoid building up a large stack of destinations
                                                         // on the back stack as users select items
                                                         popUpTo(navController.graph.findStartDestination().id) {
                                                             saveState = true
                                                         }
                                                         // Avoid multiple copies of the same destination when
                                                         // reselecting the same item
                                                         launchSingleTop = true
                                                         // Restore state when reselecting a previously selected item
                                                         restoreState = true
                                                     }
                                                 }
                            )
                        }

                }
            }
        ) { innerPadding ->
            NavHost(navController,
                    startDestination = Screen.Home.Feed.route,
                    Modifier.padding(innerPadding)) {
                this.addHomeGraph()
            }
        }
    }
}
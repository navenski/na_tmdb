package com.navektest.tmdb.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.navektest.common_feature.navigation.compose.screen.Screen
import com.navektest.tmdb.ui.theme.TmdbTheme



private fun NavGraphBuilder.tmdbNavGraph(
    onSnackSelected: (Long, NavBackStackEntry) -> Unit,
    upPress: () -> Unit
) {
    navigation(
        route = Screen.Home.route,
        startDestination = Screen.Home.Feed.route
    ) {
        addHomeGraph(onSnackSelected)
    }
//    composable(
//        "${MainDestinations.SNACK_DETAIL_ROUTE}/{${MainDestinations.SNACK_ID_KEY}}",
//        arguments = listOf(navArgument(MainDestinations.SNACK_ID_KEY) { type = NavType.LongType })
//    ) { backStackEntry ->
//        val arguments = requireNotNull(backStackEntry.arguments)
//        val snackId = arguments.getLong(MainDestinations.SNACK_ID_KEY)
//        SnackDetail(snackId, upPress)
//    }
}


fun NavGraphBuilder.addHomeGraph(
    onSnackSelected: (Long, NavBackStackEntry) -> Unit,
    modifier: Modifier = Modifier
) {
    composable(Screen.Home.Feed.route) {
        Profile()
    }
    composable(Screen.Home.Search.route) {
        Profile()
    }
    composable(Screen.Home.Video.route) {
        Profile()
    }
    composable(Screen.Home.You.route) {
        Profile()
    }
}

@Composable
fun Profile(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
            .padding(24.dp)
    ) {

        Spacer(Modifier.height(24.dp))
        Text(
            text = "stringResource(R.string.work_in_progress)",
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "stringResource(R.string.grab_beverage)",
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun ProfilePreview() {
    TmdbTheme {
        Profile()
    }
}
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.navektest.common_feature.navigation.compose.screen.Screen
import com.navektest.feed.view.FeedScreen
import com.navektest.tmdb.R
import com.navektest.tmdb.ui.theme.TmdbTheme

fun NavGraphBuilder.addHomeGraph() {
    composable(Screen.Home.Feed.route) {
        FeedScreen()
    }
    composable(Screen.Home.Search.route) {
        GenericScreen("Search")
    }
    composable(Screen.Home.Video.route) {
        GenericScreen("Video")
    }
    composable(Screen.Home.You.route) {
        GenericScreen("You")
    }
}

@Composable
fun GenericScreen(title: String = "Hello", modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
            .padding(24.dp)
    ) {

        Spacer(Modifier.height(24.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "work in progress",
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
        GenericScreen()
    }
}
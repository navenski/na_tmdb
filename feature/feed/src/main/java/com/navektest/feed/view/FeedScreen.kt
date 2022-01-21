package com.navektest.feed.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.navektest.feed.model.FeedCategory
import com.navektest.feed.viewmodel.FeedViewModel

@Composable
fun FeedScreen() {
    val viewModel: FeedViewModel = hiltViewModel()
    val state by viewModel.mediaCategoryLiveData.observeAsState()
    return Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Tmdb") }) }
    ) {
        Column {
            Button(onClick = { viewModel.testNavigation()}) {
                Text(text = "Navigate to Details", style = MaterialTheme.typography.button)
            }
        }
    }
}

@Composable
internal fun FeedCategoryView(feedCategory: FeedCategory) {
}
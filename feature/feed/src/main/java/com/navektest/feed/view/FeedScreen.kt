package com.navektest.feed.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.navektest.feed.model.FeedCategory
import com.navektest.feed.viewmodel.FeedViewModel

@Composable
fun ShowFeed() {
    val viewModel: FeedViewModel = viewModel()
    val state by viewModel.mediaCategoryLiveData.observeAsState()
    return Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Tmdb") }) }
    ) {
        Column {
            LazyColumn {
            }
        }
    }
}

@Composable
internal fun FeedCategoryView(feedCategory: FeedCategory) {
}
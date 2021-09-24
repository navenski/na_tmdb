package com.navektest.tmdb

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.navektest.tmdb.ui.theme.TmdbTheme

@Composable
fun ComposeScreen() {
    val model: ComposeViewModel = viewModel()
    val count by model.count.observeAsState(1)
    Column {
        ActionButton("increment") { model.incrementCount() }
        ActionButton("decrement") { model.decrementCount() }
        Text("count: $count")
    }
}

@Composable
fun ActionButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.padding(16.dp)) { Text(text = text) }
}


@Preview(showBackground = true)
@Composable
fun ComposeScreenPreview() {
    ComposeScreen()
}

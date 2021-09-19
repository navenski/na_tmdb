package com.navektest.search.model

import android.service.quicksettings.Tile

sealed class SearchItem {
    data class Title(val title: String) : SearchItem()
    data class Content(val id: Int, val title: String, val picturePath: String)
}
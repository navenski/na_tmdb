package com.navektest.common_feature.navigation.compose.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.navektest.common_feature.R
import com.navektest.router.Route

sealed class HomeSection(route: String, @StringRes title: Int, @DrawableRes val icon: Int) : Screen(route, title)
sealed class Screen(override val route: String, @StringRes val title: Int) : Route {

    object Home : Screen(" home", 0) {
        object Feed : HomeSection("home/feed", R.string.feed, R.drawable.ic_baseline_home_24)
        object Search : HomeSection("home/search", R.string.search, R.drawable.ic_baseline_search_24)
        object Video : HomeSection("home/video", R.string.video, R.drawable.ic_baseline_video_library_24)
        object You : HomeSection("home/you", R.string.you, R.drawable.ic_baseline_person_24)
    }

    object Details : Screen("details", 0)
}
package com.navektest.tmdb.navigation

import android.app.Activity
import android.content.Intent
import com.navektest.common_feature.navigation.StartScreenNavigation
import com.navektest.tmdb.ComposeActivity
import com.navektest.tmdb.MainActivity
import javax.inject.Inject

class StartScreenNavigationImpl @Inject constructor(private val context: Activity) : StartScreenNavigation {

    override fun navigate() {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

}
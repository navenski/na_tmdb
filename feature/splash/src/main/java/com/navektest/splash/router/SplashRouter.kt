package com.navektest.splash.router

import android.app.Activity
import com.navektest.common_feature.navigation.StartScreenNavigation
import javax.inject.Inject

class SplashRouter @Inject constructor(private val context: Activity,
                                       private val startScreenNavigation: StartScreenNavigation) {
    fun close() {
        context.finish()
    }

    fun navigateToStartScreen() {
        startScreenNavigation.navigate()
    }
}
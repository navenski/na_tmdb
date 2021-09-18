package com.navektest.splash

import androidx.lifecycle.ViewModel
import com.navektest.splash.router.SplashRouter
import java.lang.ref.WeakReference

internal class SplashViewModel : ViewModel() {
    private var routerWeakRef: WeakReference<SplashRouter> = WeakReference<SplashRouter>(null)
    fun bindRouter(router: SplashRouter) {
        routerWeakRef = WeakReference(router)
    }

    fun triggerStart() {
        routerWeakRef.get()
            ?.navigateToStartScreen()

        routerWeakRef.get()
            ?.close()
    }
}
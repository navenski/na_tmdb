package com.navektest.router

/**
 * Handle navigation
 */
interface Router<T: Route> {
    fun navigateTo(route: T)
}
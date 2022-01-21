package com.navektest.router_template

import com.navektest.router.Route
import com.navektest.router.Router

abstract class RouterTemplate<T: Route> (private val executor: RouterHandler<T>) : Router<T> {
    override fun navigateTo(route: T) {
        executor.invoke(route)
    }
}
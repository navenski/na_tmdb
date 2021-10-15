package com.navektest.router_implem

import com.navektest.router.Route
import com.navektest.router.Router
import javax.inject.Inject

abstract class RouterTemplate<T: Route> (private val executor: RouterHandler<T>) : Router<T> {
    override fun navigateTo(route: T) {
        executor.invoke(route)
    }
}
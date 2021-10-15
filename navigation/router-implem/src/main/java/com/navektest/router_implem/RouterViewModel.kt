package com.navektest.router_implem

import androidx.lifecycle.ViewModel
import com.navektest.router.Route

abstract class RouterViewModel<T : Route>(routerHandler: RouterHandler<T>) : ViewModel() {
    val flow = routerHandler.flow
}
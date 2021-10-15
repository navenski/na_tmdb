package com.navektest.router_implem

import com.navektest.router.Route
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

interface RouterHandler<T : Route> {
    val flow: Flow<T>
    operator fun invoke(screen: T)
}

class RouterHandlerImpl<T : Route> : RouterHandler<T> {
    private val sharedFlow = MutableSharedFlow<T>()
    override val flow: Flow<T> get() = sharedFlow

    override fun invoke(screen: T) {
        sharedFlow.tryEmit(screen)
    }
}
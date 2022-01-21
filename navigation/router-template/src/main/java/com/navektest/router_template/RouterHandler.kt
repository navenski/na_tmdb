package com.navektest.router_template

import android.util.Log
import com.navektest.router.Route
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

interface RouterHandler<T : Route> {
    val flow: Flow<T>
    operator fun invoke(screen: T)
}

abstract class RouterHandlerTemplate<T : Route> : RouterHandler<T> {
    private val sharedFlow = MutableSharedFlow<T>(extraBufferCapacity = 1)
    override val flow: Flow<T> get() = sharedFlow

    override fun invoke(screen: T) {
        val result = sharedFlow.tryEmit(screen)
        if (sharedFlow.subscriptionCount.value > 0 && result){
            Log.v("RouterHandler", "more than one subscribers")
        }
    }
}
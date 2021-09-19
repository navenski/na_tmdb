package com.navektest.toolkit.dispatcher

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class CoroutineDispatcherProviderImpl @Inject constructor(): CoroutineDispatcherProvider {

    init {
        Log.v("Initialiaze", "")
    }
    override fun default(): CoroutineDispatcher = Dispatchers.Default

    override fun io(): CoroutineDispatcher = Dispatchers.IO

    override fun main(): CoroutineDispatcher = Dispatchers.Main
}
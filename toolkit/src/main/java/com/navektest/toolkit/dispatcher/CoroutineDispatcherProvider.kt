package com.navektest.toolkit.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Provide [CoroutineDispatcher]
 * default
 * io
 * main
 */
interface CoroutineDispatcherProvider {
    fun default() : CoroutineDispatcher
    fun io() : CoroutineDispatcher
    fun main() : CoroutineDispatcher
}
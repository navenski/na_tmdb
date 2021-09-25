package com.navektest.usecase.common

import kotlinx.coroutines.flow.Flow

interface UseCase<in P, out T> {
    fun execute(parameter: P) : Flow<T>
}
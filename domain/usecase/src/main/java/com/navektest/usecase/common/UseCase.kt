package com.navektest.usecase.common

import kotlinx.coroutines.flow.Flow

interface UseCase<in P>

interface ResultUseCase<in P, out T> {
    operator fun invoke(parameter: P): Flow<T>
}

interface ObservableUseCase<in P, out T> {
    operator fun invoke(parameter: P)
    fun flow(): Flow<T>
}

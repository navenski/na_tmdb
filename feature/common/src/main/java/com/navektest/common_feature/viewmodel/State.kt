package com.navektest.common_feature.viewmodel

enum class DataState { LOADING, SUCCESS, ERROR, IDLE }

open class State<T> protected constructor(
    open val state: DataState,
    open val data: T? = null,
    open val error: Throwable? = null
) {
    fun hasSuccess(): Boolean {
        return data != null
    }

    fun hasError(): Boolean {
        return error != null
    }

    fun isLoading(): Boolean {
        return state == DataState.LOADING
    }

    companion object {
        fun <T> loading(data: T? = null): State<T> {
            return State(
                DataState.LOADING,
                data,
                null
            )
        }

        fun <T> error(error: Throwable? = null): State<T> {
            return State(
                DataState.ERROR,
                null,
                error
            )
        }

        fun <T> success(data: T): State<T> {
            return State(
                DataState.SUCCESS,
                data,
                null
            )
        }

        fun <T> idle(data: T? = null): State<T> {
            return State(
                DataState.IDLE,
                data,
                null
            )
        }

        fun <T> accumulate(
            oldState: State<T>,
            newState: State<T>
        ): State<T> {

            if (oldState.hasError() && newState.isLoading()) {
                return State(DataState.LOADING, oldState.data, oldState.error)
            }
            if (oldState.hasSuccess() && newState.isLoading()) {
                return State(DataState.LOADING, oldState.data, null)
            }
            if (newState.hasError()) {
                return State(DataState.ERROR, oldState.data, newState.error)
            }
            if (!newState.hasSuccess()) {
                return State(newState.state, oldState.data, null)
            }

            return newState
        }
    }
}
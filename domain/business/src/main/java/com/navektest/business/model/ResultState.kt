package com.navektest.business.model

import kotlin.Exception

sealed class ResultState<T> {
    abstract class Error<T>(val errorMessage: String = "", val exception: Exception? = null) : ResultState<T>()
    data class Success<T> internal constructor(val data: T) : ResultState<T>()

    class ServerError<T> internal constructor(msg: String = "Server error", exception: Exception? = null) :
        Error<T>(errorMessage = msg, exception = exception)

    class NoConnection<T> internal constructor(msg: String = "No connection", exception: Exception? = null) :
        Error<T>(errorMessage = msg, exception = exception)

    class Unknown<T> internal constructor(msg: String = "Unknown error", exception: Exception? = null) :
        Error<T>(errorMessage = msg, exception = exception)

    companion object {
        fun <T> success(data: T): ResultState<T> = Success(data)
        fun <T> error(message: String = "Server error", exception: Exception? = null): ResultState<T> =
            ServerError(msg = message, exception = exception)

        fun <T> noConnection(exception: Exception? = null): ResultState<T> = ServerError<T>(exception = exception)
    }
}
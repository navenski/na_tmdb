package com.navektest.business.model

sealed class ResultState<T> {
    abstract class Error(val errorMessage: String = "") : ResultState<Nothing>()
    data class Success<T>(val data: T) : ResultState<T>()

    class ServerError(msg: String = "Server error") : Error(errorMessage = msg)
    class NoConnection(msg: String = "No connection") : Error(errorMessage = msg)
    class Unknown(msg: String = "Unknown error") : Error(errorMessage = msg)

    companion object{
        fun <T>success(data: T) = Success(data)
        fun error() = ServerError()
    }
}
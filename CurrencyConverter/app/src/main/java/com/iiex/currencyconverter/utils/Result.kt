package com.iiex.currencyconverter.utils

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()

    data class Error(val exception: Throwable? = null, val message: String? = exception?.localizedMessage) : Result<Nothing>()

    object Loading : Result<Nothing>()

    companion object {
        fun <T> success(data: T): Result<T> = Success(data)

        fun error(exception: Throwable? = null, message: String? = exception?.localizedMessage): Result<Nothing> = Error(exception, message)

        fun loading(): Result<Nothing> = Loading
    }
}

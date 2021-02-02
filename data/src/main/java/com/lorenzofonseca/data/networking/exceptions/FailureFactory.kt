package com.lorenzofonseca.data.networking.exceptions

open class FailureFactory {
    open fun handleHttpError(code: Int, message: String) = HttpError(code, message)
    open fun handleNetworkError(throwable: Throwable) = NetworkError(throwable)
}
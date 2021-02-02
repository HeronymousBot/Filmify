package com.lorenzofonseca.data.networking.exceptions

import com.lorenzofonseca.domain.base.Failure

sealed class ApiError : Failure()

data class HttpError(val code: Int, val body: String) : ApiError()

data class NetworkError(val throwable: Throwable) : ApiError()

data class UnknownApiError(val throwable: Throwable) : ApiError()

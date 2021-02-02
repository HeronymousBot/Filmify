package com.lorenzofonseca.data.util

import com.lorenzofonseca.data.networking.exceptions.FailureFactory
import com.lorenzofonseca.domain.base.Either
import com.lorenzofonseca.domain.base.Failure
import retrofit2.Response
import java.io.IOException

inline fun <T, R> safeCall(
    block: () -> Response<T>,
    transform: (T?) -> R,
    errorFactory: FailureFactory = FailureFactory()
): Either<Failure, R> =
    try {
        val response = block()
        when (response.isSuccessful) {
            true -> Either.Right(transform(response.body()))
            false -> Either.Left(errorFactory.handleHttpError(response.code(), response.message()))
        }
    } catch (exception: IOException) {
        Either.Left(errorFactory.handleNetworkError(throwable = exception))
    }

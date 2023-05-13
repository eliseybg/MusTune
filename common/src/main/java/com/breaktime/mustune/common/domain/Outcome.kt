package com.breaktime.mustune.common.domain

import com.breaktime.mustune.common.exceptions.NoConnectivityException
import com.breaktime.mustune.common.exceptions.ServiceException

sealed class Outcome<out T> {
    sealed class Success<T> : Outcome<T>() {
        open val value: T get() = error("No value")

        data class Value<T>(override val value: T) : Success<T>()
        object Empty : Success<Nothing>()
    }

    sealed class Failure : Outcome<Nothing>() {
        data class ServiceError(val code: ErrorCode) : Failure()
        object Connection : Failure()
        data class Unknown(val message: String? = null) : Failure()
    }
}

enum class ErrorCode(private val code: Int) {
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500),
    Unknown(0);

    companion object {
        fun fromCode(code: Int) = values().firstOrNull { it.code == code } ?: Unknown
    }
}

fun Throwable.toFailure(): Outcome.Failure = when (this) {
    is ServiceException -> Outcome.Failure.ServiceError(ErrorCode.fromCode(code))
    is NoConnectivityException -> Outcome.Failure.Connection
    else -> Outcome.Failure.Unknown(message)
}
package com.breaktime.mustune.common.domain

sealed class Outcome<out T> {
    object Progress : Outcome<Nothing>()

    sealed class Success<T> : Outcome<T>() {
        open val value: T get() = error("No value")

        data class Value<T>(override val value: T) : Success<T>()
        object Empty : Success<Nothing>()
    }

    data class Failure<T: Throwable>(val e: T) : Outcome<Nothing>()
}
package com.breaktime.mustune.common.extentions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T, R> Flow<T>.mapWithPrev(transform: (Pair<T, R>?, T) -> R): Flow<R> {
    var prevValue: Pair<T, R>? = null
    return flow {
        collect { newValue ->
            val result = transform(prevValue, newValue)
            prevValue = newValue to result
            emit(result)
        }
    }
}
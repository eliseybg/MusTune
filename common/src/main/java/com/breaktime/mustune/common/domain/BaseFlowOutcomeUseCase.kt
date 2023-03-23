package com.breaktime.mustune.common.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class BaseFlowOutcomeUseCase<out Type, in Params> {
    operator fun invoke(params: Params): Flow<Outcome<Type>> = execute(params)
        .catch { e -> emit(Outcome.Failure(e)) }
        .flowOn(Dispatchers.IO)

    protected abstract fun execute(params: Params): Flow<Outcome<Type>>
}

package com.breaktime.mustune.common.domain

abstract class BaseOutcomeUseCase<out Type, in Params> {
    open suspend operator fun invoke(params: Params): Outcome<Type> {
        return try {
            execute(params)
        } catch (throwable: Throwable) {
            throwable.toFailure()
        }
    }

    protected abstract suspend fun execute(params: Params): Outcome<Type>
}
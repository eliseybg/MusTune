package com.breaktime.mustune.session_manager.impl.domain.use_case

import com.breaktime.mustune.common.domain.BaseOutcomeUseCase
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.session_manager.impl.domain.repository.SessionRepository
import javax.inject.Inject

class IsAuthorizedUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) : BaseOutcomeUseCase<Boolean, IsAuthorizedUseCase.Params>() {
    override suspend fun execute(params: Params): Outcome<Boolean> {
        val token = sessionRepository.getUserInfo()?.registerToken.orEmpty()
        return Outcome.Success.Value(token.isNotBlank())
    }

    object Params
}
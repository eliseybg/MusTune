package com.breaktime.mustune.session_manager.impl.domain.use_case

import com.breaktime.mustune.common.domain.BaseUseCase
import com.breaktime.mustune.session_manager.impl.domain.repository.SessionRepository
import javax.inject.Inject

class IsAuthorizedUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) : BaseUseCase<Boolean, IsAuthorizedUseCase.Params>() {
    override suspend fun execute(params: Params): Boolean {
        val token = sessionRepository.getUserInfo()?.registerToken.orEmpty()
        return token.isNotBlank()
    }

    object Params
}
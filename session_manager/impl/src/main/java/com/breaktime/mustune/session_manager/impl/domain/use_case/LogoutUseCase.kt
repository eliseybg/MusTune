package com.breaktime.mustune.session_manager.impl.domain.use_case

import com.breaktime.mustune.common.domain.BaseUseCase
import com.breaktime.mustune.session_manager.impl.domain.repository.SessionRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) : BaseUseCase<Unit, LogoutUseCase.Params>() {
    override suspend fun execute(params: Params) {
        sessionRepository.logout()
    }

    object Params
}
package com.breaktime.mustune.session_manager.impl.domain.use_case

import com.breaktime.mustune.common.domain.BaseUseCase
import com.breaktime.mustune.session_manager.impl.domain.repository.SessionRepository
import javax.inject.Inject

class LogoutAndDeleteUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) : BaseUseCase<Unit, LogoutAndDeleteUseCase.Params>() {
    override suspend fun execute(params: Params) {
        sessionRepository.logoutAndDelete()
    }

    object Params
}
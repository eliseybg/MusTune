package com.breaktime.mustune.session_manager.impl.domain.use_case

import com.breaktime.mustune.common.domain.BaseUseCase
import com.breaktime.mustune.session_manager.impl.domain.repository.SessionRepository
import javax.inject.Inject

class GetUserTokenUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) : BaseUseCase<String, GetUserTokenUseCase.Params>() {
    override suspend fun execute(params: Params): String {
        return sessionRepository.getUserInfo()?.token.orEmpty()
    }

    object Params
}
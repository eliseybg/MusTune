package com.breaktime.mustune.session_manager.impl.domain.use_case

import com.breaktime.mustune.common.domain.BaseOutcomeUseCase
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.session_manager.api.models.UserInfo
import com.breaktime.mustune.session_manager.impl.data.entities.toUserInfo
import com.breaktime.mustune.session_manager.impl.domain.repository.SessionRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) : BaseOutcomeUseCase<UserInfo, RegisterUseCase.Params>() {
    override suspend fun execute(params: Params): Outcome<UserInfo> {
        val userInfo = sessionRepository.register(params.username, params.email, params.password)
        return Outcome.Success.Value(userInfo.toUserInfo())
    }

    data class Params(val username: String, val email: String, val password: String)
}
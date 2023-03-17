package com.breaktime.mustune.session_manager.impl.domain.use_case

import com.breaktime.mustune.common.domain.BaseOutcomeUseCase
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.session_manager.api.models.UserInfo
import com.breaktime.mustune.session_manager.impl.data.entities.toUserInfo
import com.breaktime.mustune.session_manager.impl.domain.repository.SessionRepository
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) : BaseOutcomeUseCase<UserInfo, GetUserDataUseCase.Params>() {
    override suspend fun execute(params: Params): Outcome<UserInfo> {
        val userInfo = sessionRepository.getUserInfo()?.toUserInfo() ?: return Outcome.Success.Empty
        return Outcome.Success.Value(userInfo)
    }

    object Params
}
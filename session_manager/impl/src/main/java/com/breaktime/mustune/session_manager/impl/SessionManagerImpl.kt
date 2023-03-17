package com.breaktime.mustune.session_manager.impl

import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.network.api.TokenProvider
import com.breaktime.mustune.session_manager.api.SessionManager
import com.breaktime.mustune.session_manager.api.models.UserInfo
import com.breaktime.mustune.session_manager.impl.domain.use_case.GetUserDataUseCase
import com.breaktime.mustune.session_manager.impl.domain.use_case.GetUserTokenUseCase
import com.breaktime.mustune.session_manager.impl.domain.use_case.IsAuthorizedUseCase
import com.breaktime.mustune.session_manager.impl.domain.use_case.LoginUseCase
import com.breaktime.mustune.session_manager.impl.domain.use_case.LogoutUseCase
import com.breaktime.mustune.session_manager.impl.domain.use_case.RegisterUseCase
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SessionManagerImpl @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val isAuthorizedUseCase: IsAuthorizedUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getUserTokenUseCase: GetUserTokenUseCase
) : SessionManager, TokenProvider {
    override suspend fun login(email: String, password: String): Outcome<UserInfo> {
        return loginUseCase(LoginUseCase.Params(email, password))
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): Outcome<UserInfo> {
        return registerUseCase(RegisterUseCase.Params(username, email, password))
    }

    override suspend fun logout() {
        logoutUseCase(LogoutUseCase.Params)
    }

    override suspend fun isAuthorized(): Outcome<Boolean> {
        return isAuthorizedUseCase(IsAuthorizedUseCase.Params)
    }

    override suspend fun getUserData(): Outcome<UserInfo> {
        return getUserDataUseCase(GetUserDataUseCase.Params)
    }

    override fun getToken(): String {
        return runBlocking { getUserTokenUseCase(GetUserTokenUseCase.Params) }
    }
}
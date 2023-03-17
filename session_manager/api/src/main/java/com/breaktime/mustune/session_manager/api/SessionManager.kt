package com.breaktime.mustune.session_manager.api

import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.session_manager.api.models.UserInfo

interface SessionManager {
    suspend fun login(email: String, password: String): Outcome<UserInfo>
    suspend fun register(username: String, email: String, password: String): Outcome<UserInfo>
    suspend fun logout()
    suspend fun isAuthorized(): Outcome<Boolean>
    suspend fun getUserData(): Outcome<UserInfo>
}
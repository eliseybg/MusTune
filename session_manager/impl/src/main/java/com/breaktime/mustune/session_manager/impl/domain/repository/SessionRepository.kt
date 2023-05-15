package com.breaktime.mustune.session_manager.impl.domain.repository

import com.breaktime.mustune.session_manager.impl.data.entities.UserInfoEntity

interface SessionRepository {
    suspend fun login(email: String, password: String): UserInfoEntity
    suspend fun register(username: String, email: String, password: String): UserInfoEntity
    suspend fun logout()
    suspend fun logoutAndDelete()
    suspend fun deleteAccount()
    suspend fun getUserInfo(): UserInfoEntity?
}
package com.breaktime.mustune.session_manager.impl.data.source

import com.breaktime.mustune.network.api.extentions.retrieveBody
import com.breaktime.mustune.session_manager.impl.data.entities.LoginBody
import com.breaktime.mustune.session_manager.impl.data.entities.SignupBody
import com.breaktime.mustune.session_manager.impl.data.entities.UserInfoEntity
import com.breaktime.mustune.session_manager.impl.data.source.remote.SessionApiService
import com.breaktime.mustune.session_manager.impl.domain.repository.SessionRepository
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sessionApiService: SessionApiService,
    private val dataSource: DataSource
) : SessionRepository {
    override suspend fun login(email: String, password: String): UserInfoEntity {
        val body = LoginBody(email, password)
        val response = sessionApiService.login(body)
        return response.retrieveBody().also { dataSource.setUserInfo(it) }
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): UserInfoEntity {
        val body = SignupBody(username, email, password)
        val response = sessionApiService.signup(body)
        return response.retrieveBody().also { dataSource.setUserInfo(it) }
    }

    override suspend fun logout() {
        dataSource.removeUserInfo()
    }

    override suspend fun getUserInfo(): UserInfoEntity? {
        return dataSource.getUserInfo()
    }
}
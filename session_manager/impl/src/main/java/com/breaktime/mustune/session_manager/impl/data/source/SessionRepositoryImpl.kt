package com.breaktime.mustune.session_manager.impl.data.source

import com.breaktime.mustune.network.api.extentions.retrieveBody
import com.breaktime.mustune.session_manager.impl.data.entities.UserInfoEntity
import com.breaktime.mustune.session_manager.impl.data.source.remote.SessionApiService
import com.breaktime.mustune.session_manager.impl.domain.repository.SessionRepository
import java.lang.Exception
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sessionApiService: SessionApiService,
    private val dataSource: DataSource
) : SessionRepository {
    override suspend fun login(email: String, password: String): UserInfoEntity {
        val response = sessionApiService.login(email, password)
        return response.retrieveBody().also { dataSource.setUserInfo(it) }
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): UserInfoEntity {
        val response = sessionApiService.signup(username, email, password)
        return response.retrieveBody().also { dataSource.setUserInfo(it) }
    }

    override suspend fun checkUserToken(token: String) {
        sessionApiService.checkToken(token)
    }

    override suspend fun logout() {
        dataSource.removeUserInfo()
    }

    override suspend fun getUserInfo(): UserInfoEntity? {
        return dataSource.getUserInfo()
    }
}
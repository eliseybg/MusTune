package com.breaktime.mustune.session_manager.impl.data.source

import com.breaktime.mustune.network.api.extentions.retrieveBody
import com.breaktime.mustune.session_manager.impl.data.entities.LoginBody
import com.breaktime.mustune.session_manager.impl.data.entities.SignupBody
import com.breaktime.mustune.session_manager.impl.data.entities.UserInfoEntity
import com.breaktime.mustune.session_manager.impl.data.source.remote.SessionApiService
import com.breaktime.mustune.session_manager.impl.domain.repository.SessionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sessionApiService: SessionApiService,
    private val dataSource: DataSource
) : SessionRepository {
    override suspend fun login(
        email: String, password: String
    ): UserInfoEntity = withContext(Dispatchers.IO) {
        val body = LoginBody(email, password)
        val response = sessionApiService.login(body)
        response.retrieveBody().also { dataSource.setUserInfo(it) }
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): UserInfoEntity = withContext(Dispatchers.IO) {
        val body = SignupBody(username, email, password)
        val response = sessionApiService.signup(body)
        response.retrieveBody().also { dataSource.setUserInfo(it) }
    }

    override suspend fun logout() = withContext(Dispatchers.IO) {
        dataSource.removeUserInfo()
    }

    override suspend fun deleteAccount() = withContext(Dispatchers.IO) {
        dataSource.removeUserInfo()
    }

    override suspend fun getUserInfo(): UserInfoEntity? = withContext(Dispatchers.IO) {
        dataSource.getUserInfo()
    }
}
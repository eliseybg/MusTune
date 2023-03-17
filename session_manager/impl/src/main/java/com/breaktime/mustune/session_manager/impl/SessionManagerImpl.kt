package com.breaktime.mustune.session_manager.impl

import com.breaktime.mustune.network.api.TokenProvider
import com.breaktime.mustune.session_manager.api.SessionManager
import javax.inject.Inject

class SessionManagerImpl @Inject constructor() : SessionManager, TokenProvider {
    override suspend fun login() {

    }

    override suspend fun register() {

    }

    override suspend fun logout() {

    }

    override fun getToken(): String {
        return "token"
    }
}
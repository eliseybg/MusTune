package com.breaktime.mustune.session_manager.impl.domain

interface SessionRepository {
    suspend fun login()
    suspend fun register()
    suspend fun logout()
}
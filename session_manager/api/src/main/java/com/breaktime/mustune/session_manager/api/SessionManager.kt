package com.breaktime.mustune.session_manager.api

interface SessionManager {
    suspend fun login()
    suspend fun register()
    suspend fun logout()
}
package com.breaktime.mustune.data.api.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun setNotificationsEnabled(isEnabled: Boolean)
    fun isNotificationsEnabled(): Flow<Boolean>

    suspend fun setDarkModeEnabled(isEnabled: Boolean)
    fun isDarkModeEnabled(): Flow<Boolean>

    suspend fun setCurrentLanguage(language: String)
    fun getCurrentLanguage(): Flow<String>
}
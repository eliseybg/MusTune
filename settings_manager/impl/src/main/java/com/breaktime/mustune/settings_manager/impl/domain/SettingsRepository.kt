package com.breaktime.mustune.settings_manager.impl.domain

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun setNotificationsEnabled(isEnabled: Boolean)
    fun isNotificationsEnabled(): Flow<Boolean>

    suspend fun setDarkModeEnabled(isEnabled: Boolean)
    fun isDarkModeEnabled(): Flow<Boolean>

    suspend fun setCurrentLanguage(language: String)
    fun getCurrentLanguage(): Flow<String>
}
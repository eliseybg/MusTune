package com.breaktime.mustune.settings.api.data.data_source

import kotlinx.coroutines.flow.Flow

interface SettingsDataSource {
    suspend fun setNotificationsEnabled(isEnabled: Boolean)
    fun isNotificationsEnabled(): Flow<Boolean>

    suspend fun setDarkModeEnabled(isEnabled: Boolean)
    fun isDarkModeEnabled(): Flow<Boolean>

    suspend fun setCurrentLanguage(isEnabled: Boolean)
    fun getCurrentLanguage(): Flow<Boolean>
}
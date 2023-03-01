package com.breaktime.mustune.data.impl.repository.settings.data_source

import kotlinx.coroutines.flow.Flow

interface SettingsDataSource {
    suspend fun setNotificationsEnabled(isEnabled: Boolean)
    fun isNotificationsEnabled(): Flow<Boolean>

    suspend fun setDarkModeEnabled(isEnabled: Boolean)
    fun isDarkModeEnabled(): Flow<Boolean>

    suspend fun setCurrentLanguage(language: String)
    fun getCurrentLanguage(): Flow<String>
}
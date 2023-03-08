package com.breaktime.mustune.settings_manager.impl

import com.breaktime.mustune.settings_manager.api.SettingsManager
import com.breaktime.mustune.settings_manager.impl.domain.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsManagerImpl @Inject constructor(
    private val settingsRepository: SettingsRepository
) : SettingsManager {
    override suspend fun setNotificationsEnabled(isEnabled: Boolean) {
        settingsRepository.setNotificationsEnabled(isEnabled)
    }

    override fun isNotificationsEnabled(): Flow<Boolean> {
        return settingsRepository.isNotificationsEnabled()
    }

    override suspend fun setDarkModeEnabled(isEnabled: Boolean) {
        settingsRepository.setDarkModeEnabled(isEnabled)
    }

    override fun isDarkModeEnabled(): Flow<Boolean> {
        return settingsRepository.isDarkModeEnabled()
    }

    override suspend fun setCurrentLanguage(language: String) {
        settingsRepository.setCurrentLanguage(language)
    }

    override fun getCurrentLanguage(): Flow<String> {
        return settingsRepository.getCurrentLanguage()
    }

}
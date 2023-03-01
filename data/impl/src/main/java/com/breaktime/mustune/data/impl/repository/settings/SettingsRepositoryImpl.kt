package com.breaktime.mustune.data.impl.repository.settings

import com.breaktime.mustune.data.impl.repository.settings.data_source.SettingsDataSource
import com.breaktime.mustune.data.api.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val local: SettingsDataSource
) : SettingsRepository {
    override suspend fun setNotificationsEnabled(isEnabled: Boolean) {
        local.setNotificationsEnabled(isEnabled)
    }

    override fun isNotificationsEnabled() = local.isNotificationsEnabled()

    override suspend fun setDarkModeEnabled(isEnabled: Boolean) {
        local.setDarkModeEnabled(isEnabled)
    }

    override fun isDarkModeEnabled() = local.isDarkModeEnabled()

    override suspend fun setCurrentLanguage(language: String) {
        local.setCurrentLanguage(language)
    }

    override fun getCurrentLanguage() = local.getCurrentLanguage()
}
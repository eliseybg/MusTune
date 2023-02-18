package com.breaktime.mustune.settings.api.data.repository

import com.breaktime.mustune.settings.api.data.data_source.SettingsDataSource
import com.breaktime.mustune.settings.api.domain.repository.SettingsRepository
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

    override suspend fun setCurrentLanguage(isEnabled: Boolean) {
        local.setCurrentLanguage(isEnabled)
    }

    override fun getCurrentLanguage() = local.getCurrentLanguage()
}
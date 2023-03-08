package com.breaktime.mustune.settings_manager.impl.data.source

import android.content.SharedPreferences
import androidx.compose.ui.text.intl.Locale
import androidx.core.content.edit
import com.breaktime.mustune.common.Constants
import com.breaktime.mustune.common.extentions.observeKey
import com.breaktime.mustune.settings_manager.impl.domain.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val preferences: SharedPreferences
) : SettingsRepository {
    override suspend fun setNotificationsEnabled(isEnabled: Boolean) = preferences.edit(true) {
        putBoolean(Constants.Settings.KEY_NOTIFICATIONS_ENABLED, isEnabled)
    }

    override fun isNotificationsEnabled() =
        preferences.observeKey(Constants.Settings.KEY_NOTIFICATIONS_ENABLED, true)

    override suspend fun setDarkModeEnabled(isEnabled: Boolean) = preferences.edit {
        putBoolean(Constants.Settings.KEY_DARK_MODE_ENABLED, isEnabled)
    }

    override fun isDarkModeEnabled() =
        preferences.observeKey(Constants.Settings.KEY_DARK_MODE_ENABLED, false)

    override suspend fun setCurrentLanguage(language: String) = preferences.edit {
        putString(Constants.Settings.KEY_CURRENT_LANGUAGE, language)
    }

    override fun getCurrentLanguage() =
        preferences.observeKey(Constants.Settings.KEY_CURRENT_LANGUAGE, Locale.current.language)
}
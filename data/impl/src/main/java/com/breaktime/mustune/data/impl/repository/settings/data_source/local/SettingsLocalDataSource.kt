package com.breaktime.mustune.data.impl.repository.settings.data_source.local

import android.content.SharedPreferences
import androidx.compose.ui.text.intl.Locale
import androidx.core.content.edit
import com.breaktime.mustune.common.Constants.Settings.KEY_CURRENT_LANGUAGE
import com.breaktime.mustune.common.Constants.Settings.KEY_DARK_MODE_ENABLED
import com.breaktime.mustune.common.Constants.Settings.KEY_NOTIFICATIONS_ENABLED
import com.breaktime.mustune.common.extentions.observeKey
import com.breaktime.mustune.data.impl.repository.settings.data_source.SettingsDataSource
import javax.inject.Inject

class SettingsLocalDataSource @Inject constructor(
    private val preferences: SharedPreferences
) : SettingsDataSource {
    override suspend fun setNotificationsEnabled(isEnabled: Boolean) = preferences.edit(true) {
        putBoolean(KEY_NOTIFICATIONS_ENABLED, isEnabled)
    }

    override fun isNotificationsEnabled() = preferences.observeKey(KEY_NOTIFICATIONS_ENABLED, true)

    override suspend fun setDarkModeEnabled(isEnabled: Boolean) = preferences.edit {
        putBoolean(KEY_DARK_MODE_ENABLED, isEnabled)
    }

    override fun isDarkModeEnabled() = preferences.observeKey(KEY_DARK_MODE_ENABLED, false)

    override suspend fun setCurrentLanguage(language: String) = preferences.edit {
        putString(KEY_CURRENT_LANGUAGE, language)
    }

    override fun getCurrentLanguage() = preferences.observeKey(KEY_CURRENT_LANGUAGE, Locale.current.language)
}
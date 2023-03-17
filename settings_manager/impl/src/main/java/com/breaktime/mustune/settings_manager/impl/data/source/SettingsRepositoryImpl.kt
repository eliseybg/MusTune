package com.breaktime.mustune.settings_manager.impl.data.source

import android.content.SharedPreferences
import androidx.compose.ui.text.intl.Locale
import androidx.core.content.edit
import com.breaktime.mustune.common.extentions.observeKey
import com.breaktime.mustune.settings_manager.impl.domain.SettingsRepository
import com.breaktime.mustune.settings_manager.impl.util.Constants
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val preferences: SharedPreferences
) : SettingsRepository {
    override suspend fun setNotificationsEnabled(isEnabled: Boolean) = with(Dispatchers.IO) {
        preferences.edit(true) {
            putBoolean(Constants.KEY_NOTIFICATIONS_ENABLED, isEnabled)
        }
    }

    override fun isNotificationsEnabled() =
        preferences.observeKey(Constants.KEY_NOTIFICATIONS_ENABLED, true)

    override suspend fun setDarkModeEnabled(isEnabled: Boolean) = with(Dispatchers.IO) {
        preferences.edit {
            putBoolean(Constants.KEY_DARK_MODE_ENABLED, isEnabled)
        }
    }

    override fun isDarkModeEnabled() =
        preferences.observeKey(Constants.KEY_DARK_MODE_ENABLED, false)

    override suspend fun setCurrentLanguage(language: String) = with(Dispatchers.IO) {
        preferences.edit {
            putString(Constants.KEY_CURRENT_LANGUAGE, language)
        }
    }

    override fun getCurrentLanguage() =
        preferences.observeKey(Constants.KEY_CURRENT_LANGUAGE, Locale.current.language)
}
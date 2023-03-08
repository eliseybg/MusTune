package com.breaktime.mustune.settings_manager.api

import androidx.compose.runtime.compositionLocalOf

interface SettingsManagerProvider {
    val settingsManager: SettingsManager
}

val LocalSettingsManagerProvider = compositionLocalOf<SettingsManagerProvider> { error("No data provider found!") }
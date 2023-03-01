package com.breaktime.mustune.data.api

import androidx.compose.runtime.compositionLocalOf
import com.breaktime.mustune.data.api.repository.SettingsRepository

interface DataProvider {
    val settingsRepository: SettingsRepository
}

val LocalDataProvider = compositionLocalOf<DataProvider> { error("No data provider found!") }
package com.breaktime.mustune.di

import androidx.compose.runtime.compositionLocalOf
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.di.CommonProvider
import com.breaktime.mustune.musicmanager.api.MusicManagerProvider
import com.breaktime.mustune.settings_manager.api.SettingsManagerProvider

interface AppProvider : CommonProvider, MusicManagerProvider,
    SettingsManagerProvider {

    val destinations: Destinations
}

val LocalAppProvider = compositionLocalOf<AppProvider> { error("No app provider found!") }
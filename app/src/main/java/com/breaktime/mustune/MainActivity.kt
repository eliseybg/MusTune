package com.breaktime.mustune

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.breaktime.mustune.common.di.LocalCommonProvider
import com.breaktime.mustune.di.LocalAppProvider
import com.breaktime.mustune.file_manager.api.LocalFileManagerProvider
import com.breaktime.mustune.musicmanager.api.LocalMusicManagerProvider
import com.breaktime.mustune.settings_manager.api.LocalSettingsManagerProvider
import com.breaktime.mustune.resources.theme.MusTuneTheme
import com.breaktime.mustune.session_manager.api.LocalSessionManagerProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkMode by application.appProvider.settingsManager.isDarkModeEnabled()
                .collectAsState(null)
            isDarkMode?.let {
                MusTuneTheme(it) {
                    Surface(color = MaterialTheme.colors.background) {
                        CompositionLocalProvider(
                            LocalAppProvider provides application.appProvider,
                            LocalCommonProvider provides application.appProvider,
                            LocalFileManagerProvider provides application.appProvider,
                            LocalSessionManagerProvider provides application.appProvider,
                            LocalMusicManagerProvider provides application.appProvider,
                            LocalSettingsManagerProvider provides application.appProvider,
                        ) {
                            Navigation()
                        }
                    }
                }
            }
        }
    }
}
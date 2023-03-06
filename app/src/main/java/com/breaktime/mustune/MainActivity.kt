package com.breaktime.mustune

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.breaktime.mustune.common.di.LocalCommonProvider
import com.breaktime.mustune.data.api.LocalDataProvider
import com.breaktime.mustune.di.LocalAppProvider
import com.breaktime.mustune.musicmanager.api.LocalMusicManagerProvider
import com.breaktime.mustune.ui.theme.MusTuneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusTuneTheme {
                Surface(color = MaterialTheme.colors.background) {
                    CompositionLocalProvider(
                        LocalAppProvider provides application.appProvider,
                        LocalCommonProvider provides application.appProvider,
                        LocalDataProvider provides application.appProvider,
                        LocalMusicManagerProvider provides application.appProvider
                    ) {
                        Navigation()
                    }
                }
            }
        }
    }
}
package com.breaktime.mustune.musicmanager.api

import androidx.compose.runtime.compositionLocalOf

interface MusicManagerProvider {
    val musicManager: MusicManager
}

val LocalMusicManagerProvider = compositionLocalOf<MusicManagerProvider> { error("No data provider found!") }
package com.breaktime.mustune.di

import androidx.compose.runtime.compositionLocalOf
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.di.CommonProvider
import com.breaktime.mustune.data.api.DataProvider
import com.breaktime.mustune.musicmanager.api.MusicManagerProvider

interface AppProvider : CommonProvider, DataProvider, MusicManagerProvider {

    val destinations: Destinations
}

val LocalAppProvider = compositionLocalOf<AppProvider> { error("No app provider found!") }
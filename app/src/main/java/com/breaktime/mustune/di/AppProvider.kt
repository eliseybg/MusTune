package com.breaktime.mustune.di

import androidx.compose.runtime.compositionLocalOf
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.di.CommonProvider

interface AppProvider : CommonProvider {

    val destinations: Destinations
}

val LocalAppProvider = compositionLocalOf<AppProvider> { error("No app provider found!") }
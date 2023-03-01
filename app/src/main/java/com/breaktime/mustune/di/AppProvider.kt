package com.breaktime.mustune.di

import androidx.compose.runtime.compositionLocalOf
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.di.CommonProvider
import com.breaktime.mustune.data.api.DataProvider

interface AppProvider : CommonProvider, DataProvider {

    val destinations: Destinations
}

val LocalAppProvider = compositionLocalOf<AppProvider> { error("No app provider found!") }
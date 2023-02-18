package com.breaktime.mustune

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.breaktime.mustune.common.find
import com.breaktime.mustune.di.LocalAppProvider
import com.breaktime.mustune.settings.api.SettingsEntry

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val destinations = LocalAppProvider.current.destinations

    val loginScreen = destinations.find<SettingsEntry>()
//    val musicScreen = destinations.find<MusicEntry>()
//    val mainScreen = destinations.find<MainEntry>()
    val settingsScreen = destinations.find<SettingsEntry>()

    Box(Modifier.fillMaxSize()) {
        NavHost(navController, startDestination = loginScreen.destination()) {

            with(loginScreen) {
                composable(navController, destinations)
            }
//            with(movieDetailsScreen) {
//                navigation(navController, destinations)
//            }
        }
    }
}
package com.breaktime.mustune

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.breaktime.mustune.common.find
import com.breaktime.mustune.di.LocalAppProvider
import com.breaktime.mustune.main.api.MainEntry
import com.breaktime.mustune.music.api.MusicEntry
import com.breaktime.mustune.search_songs.api.SearchSongsEntry
import com.breaktime.mustune.settings.api.SettingsEntry

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val destinations = LocalAppProvider.current.destinations

    val mainScreen = destinations.find<MainEntry>()
    val musicScreen = destinations.find<MusicEntry>()
    val searchSongsScreen = destinations.find<SearchSongsEntry>()
    val settingsScreen = destinations.find<SettingsEntry>()

    Box(Modifier.fillMaxSize()) {
        NavHost(navController, startDestination = mainScreen.destination()) {
            with(mainScreen) {
                subNavigation(destinations, musicScreen.destination()) {
                    with(musicScreen) { composable(navController, destinations) }
                    with(settingsScreen) { composable(navController, destinations) }
                }
                with(searchSongsScreen) { composable(navController, destinations) }
            }
        }
    }
}
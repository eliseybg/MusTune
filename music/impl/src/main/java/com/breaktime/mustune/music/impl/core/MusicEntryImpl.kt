package com.breaktime.mustune.music.impl.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.di.injectedViewModel
import com.breaktime.mustune.music.api.MusicEntry
import com.breaktime.mustune.music.impl.di.DaggerMusicComponent
import com.breaktime.mustune.music.impl.presentation.MusicScreen
import com.breaktime.mustune.musicmanager.api.LocalMusicManagerProvider
import javax.inject.Inject

class MusicEntryImpl @Inject constructor() : MusicEntry() {
    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        val musicManagerProvider = LocalMusicManagerProvider.current
        val viewModelStore = remember {
            navController.currentBackStackEntry?.viewModelStore ?: backStackEntry.viewModelStore
        }
        val viewModel = injectedViewModel(viewModelStoreOwner = { viewModelStore }) {
            DaggerMusicComponent.builder()
                .musicManagerProvider(musicManagerProvider)
                .build()
                .viewModel
        }

        MusicScreen(viewModel, navController, destinations)
    }
}
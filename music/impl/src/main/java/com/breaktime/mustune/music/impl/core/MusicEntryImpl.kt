package com.breaktime.mustune.music.impl.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.di.LocalCommonProvider
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
        val commonProvider = LocalCommonProvider.current
        val musicManagerProvider = LocalMusicManagerProvider.current
        val viewModel = injectedViewModel(viewModelStoreOwner = {
            navController.currentBackStackEntry?.viewModelStore ?: backStackEntry.viewModelStore
        }) {
            DaggerMusicComponent.builder()
                .commonProvider(commonProvider)
                .musicManagerProvider(musicManagerProvider)
                .build()
                .viewModel
        }

        MusicScreen(viewModel, navController, destinations)
    }
}
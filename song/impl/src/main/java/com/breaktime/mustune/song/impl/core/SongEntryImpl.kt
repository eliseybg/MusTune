package com.breaktime.mustune.song.impl.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.di.LocalCommonProvider
import com.breaktime.mustune.common.di.injectedViewModel
import com.breaktime.mustune.musicmanager.api.LocalMusicManagerProvider
import com.breaktime.mustune.song.api.SongEntry
import com.breaktime.mustune.song.impl.di.DaggerSongComponent
import com.breaktime.mustune.song.impl.presentation.SongScreen
import javax.inject.Inject

class SongEntryImpl @Inject constructor() : SongEntry() {
    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        val commonProvider = LocalCommonProvider.current
        val musicManagerProvider = LocalMusicManagerProvider.current
        val viewModel = injectedViewModel {
            DaggerSongComponent.builder()
                .commonProvider(commonProvider)
                .musicManagerProvider(musicManagerProvider)
                .build()
                .viewModel
        }

        SongScreen(viewModel, navController, destinations)
    }
}
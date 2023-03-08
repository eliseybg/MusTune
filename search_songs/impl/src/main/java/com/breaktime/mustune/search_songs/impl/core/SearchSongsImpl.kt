package com.breaktime.mustune.search_songs.impl.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.di.LocalCommonProvider
import com.breaktime.mustune.common.di.injectedViewModel
import com.breaktime.mustune.musicmanager.api.LocalMusicManagerProvider
import com.breaktime.mustune.search_songs.api.SearchSongsEntry
import com.breaktime.mustune.search_songs.impl.di.DaggerSearchSongsComponent
import com.breaktime.mustune.search_songs.impl.presentation.SearchSongsScreen
import javax.inject.Inject

class SearchSongsImpl @Inject constructor() : SearchSongsEntry() {
    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        val commonProvider = LocalCommonProvider.current
        val musicManagerProvider = LocalMusicManagerProvider.current
        val viewModel = injectedViewModel {
            DaggerSearchSongsComponent.builder()
                .commonProvider(commonProvider)
                .musicManagerProvider(musicManagerProvider)
                .build()
                .viewModel
        }

        SearchSongsScreen(viewModel, navController)
    }
}
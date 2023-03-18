package com.breaktime.mustune.create_edit_file.impl.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.di.injectedViewModel
import com.breaktime.mustune.create_edit_file.api.CreateEditFileEntry
import com.breaktime.mustune.create_edit_file.impl.di.DaggerCreateEditFileComponent
import com.breaktime.mustune.create_edit_file.impl.presentation.CreateEditFileScreen
import javax.inject.Inject

class CreateEditFileEntryImpl @Inject constructor() : CreateEditFileEntry() {
    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        val songId = backStackEntry.arguments?.getString(ARG_SONG_ID)
        val viewModel = injectedViewModel {
            DaggerCreateEditFileComponent.builder()
                .songId(songId)
                .build()
                .viewModel
        }

        CreateEditFileScreen(viewModel, navController)
    }
}
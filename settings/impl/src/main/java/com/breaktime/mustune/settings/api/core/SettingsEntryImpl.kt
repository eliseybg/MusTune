package com.breaktime.mustune.settings.api.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.di.LocalCommonProvider
import com.breaktime.mustune.common.di.injectedViewModel
import com.breaktime.mustune.settings.api.SettingsEntry
import com.breaktime.mustune.settings.api.presentation.SettingsScreen
import com.breaktime.mustune.settings.api.di.DaggerSettingsComponent
import javax.inject.Inject

class SettingsEntryImpl @Inject constructor() : SettingsEntry() {
    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        val commonProvider = LocalCommonProvider.current
        val viewModel = injectedViewModel {
            DaggerSettingsComponent.builder()
                .commonProvider(commonProvider)
                .build()
                .viewModel
        }

        SettingsScreen(viewModel, navController)
    }
}
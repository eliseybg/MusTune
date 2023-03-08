package com.breaktime.mustune.settings.impl.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.di.LocalCommonProvider
import com.breaktime.mustune.common.di.injectedViewModel
import com.breaktime.mustune.settings.api.SettingsEntry
import com.breaktime.mustune.settings.impl.presentation.SettingsScreen
import com.breaktime.mustune.settings.impl.di.DaggerSettingsComponent
import com.breaktime.mustune.settings_manager.api.LocalSettingsManagerProvider
import javax.inject.Inject

class SettingsEntryImpl @Inject constructor() : SettingsEntry() {
    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        val commonProvider = LocalCommonProvider.current
        val settingsManagerProvider = LocalSettingsManagerProvider.current
        val viewModel = injectedViewModel {
            DaggerSettingsComponent.builder()
                .commonProvider(commonProvider)
                .settingsManagerProvider(settingsManagerProvider)
                .build()
                .viewModel
        }

        SettingsScreen(viewModel, navController)
    }
}
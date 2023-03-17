package com.breaktime.mustune.login.impl.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.breaktime.mustune.login.api.LoginEntry
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.di.LocalCommonProvider
import com.breaktime.mustune.login.impl.presentation.LoginScreen
import javax.inject.Inject

class LoginEntryImpl @Inject constructor() : LoginEntry() {
    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        val commonProvider = LocalCommonProvider.current
//        val musicManagerProvider = LocalMusicManagerProvider.current
//        val viewModel = injectedViewModel(viewModelStoreOwner = {
//            navController.currentBackStackEntry?.viewModelStore ?: backStackEntry.viewModelStore
//        }) {
//            DaggerLoginComponent.builder()
//                .commonProvider(commonProvider)
//                .musicManagerProvider(musicManagerProvider)
//                .build()
//                .viewModel
//        }

//        LoginScreen(viewModel, navController, destinations)
        LoginScreen(navController, destinations)
    }
}
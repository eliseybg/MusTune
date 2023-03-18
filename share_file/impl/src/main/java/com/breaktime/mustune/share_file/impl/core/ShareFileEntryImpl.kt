package com.breaktime.mustune.share_file.impl.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.di.injectedViewModel
import com.breaktime.mustune.share_file.api.ShareFileEntry
import com.breaktime.mustune.share_file.impl.di.DaggerShareFileComponent
import com.breaktime.mustune.share_file.impl.presentation.ShareFileScreen
import javax.inject.Inject

class ShareFileEntryImpl @Inject constructor() : ShareFileEntry() {
    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        val viewModel = injectedViewModel {
            DaggerShareFileComponent.builder()
                .build()
                .viewModel
        }

        ShareFileScreen(viewModel, navController)
    }
}
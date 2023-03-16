package com.breaktime.mustune.impl.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.find
import com.breaktime.mustune.main.api.MainEntry

@Composable
fun LoginScreen(
//    viewModel: LoginViewModel,
    navController: NavHostController,
    destinations: Destinations
) {
//    val state by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
            .clickable { navController.navigate(destinations.find<MainEntry>().featureRoute) }) {

    }
}

package com.breaktime.mustune.song.impl.presentation

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.find
import com.breaktime.mustune.search_songs.api.SearchSongsEntry
import com.breaktime.mustune.ui_kit.common.Toolbar

@Composable
fun SetScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    LaunchedEffect(orientation) {
        val activity = context.findActivity() ?: return@LaunchedEffect
        activity.requestedOrientation = orientation
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@Composable
fun SongScreen(
    viewModel: SongViewModel,
    navController: NavHostController,
    destinations: Destinations
) {
    SetScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Toolbar(
                content = {
                    Text(
                        text = "Instrument",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                },
                actions = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                val route = destinations.find<SearchSongsEntry>().featureRoute
                                navController.navigate(route)
                            },
                        imageVector = Icons.Default.Search,
                        contentDescription = "search icon",
                    )
                },
                navigation = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                val activity = context.findActivity() ?: return@clickable
                                activity.requestedOrientation =
                                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                                navController.popBackStack()
                            },
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back icon",
                    )
                }
            )
        }
    ) {

    }
}

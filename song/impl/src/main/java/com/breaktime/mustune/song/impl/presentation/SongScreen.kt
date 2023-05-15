package com.breaktime.mustune.song.impl.presentation

import android.content.Context
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.extentions.Orientation
import com.breaktime.mustune.common.extentions.setScreenOrientation
import com.breaktime.mustune.resources.R
import com.breaktime.mustune.resources.theme.MusTuneTheme
import com.breaktime.mustune.ui_kit.common.PrimaryLoadingProgress
import com.breaktime.mustune.ui_kit.common.Toolbar
import com.breaktime.mustune.ui_kit.elements.PdfViewer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun SongScreen(
    viewModel: SongViewModel,
    navController: NavHostController,
    destinations: Destinations
) {
    setScreenOrientation(Orientation.LANDSCAPE)
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val backPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModelObserver(viewModel, context, scope)
    }

    LaunchedEffect(key1 = true) {
        backPressedDispatcher?.addCallback {
            context.setScreenOrientation(Orientation.PORTRAIT)
            navController.popBackStack()
        }
    }

    if (state.isLoading) {
        PrimaryLoadingProgress()
    } else Scaffold(
        topBar = {
            Toolbar(
                content = {
                    Text(
                        text = state.songName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = MusTuneTheme.colors.content
                    )
                },
                navigation = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                context.setScreenOrientation(Orientation.PORTRAIT)
                                navController.popBackStack()
                            },
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back icon",
                        tint = MusTuneTheme.colors.content
                    )
                }
            )
        },
        backgroundColor = MusTuneTheme.colors.background
    ) {
        state.file?.let { file ->
            PdfViewer(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .padding(16.dp),
                file = file
            )
        }
    }
}

private fun viewModelObserver(
    viewModel: SongViewModel,
    context: Context,
    scope: CoroutineScope
) {
    viewModel.effect.onEach {
        when (it) {
            is SongContract.Effect.ErrorMessage -> Toast.makeText(
                context, it.message.asString(context), Toast.LENGTH_SHORT
            ).show()
        }
    }.launchIn(scope)
}

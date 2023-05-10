package com.breaktime.mustune.song_bottom_sheet

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.di.LocalCommonProvider
import com.breaktime.mustune.common.di.injectedViewModel
import com.breaktime.mustune.common.find
import com.breaktime.mustune.create_edit_file.api.CreateEditFileEntry
import com.breaktime.mustune.musicmanager.api.LocalMusicManagerProvider
import com.breaktime.mustune.musicmanager.api.models.ShareSettings
import com.breaktime.mustune.musicmanager.api.models.Song
import com.breaktime.mustune.resources.R
import com.breaktime.mustune.share_file.api.ShareFileEntry
import com.breaktime.mustune.ui_kit.common.ContentBottomSheet
import com.breaktime.mustune.ui_kit.common.ContentBottomSheetState
import com.breaktime.mustune.ui_kit.common.bottom_sheet.song_bottom_sheet.SongBottomSheet
import com.breaktime.mustune.ui_kit.common.bottom_sheet.song_bottom_sheet.SongBottomSheetContent
import com.breaktime.mustune.ui_kit.common.rememberContentBottomSheetState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.time.Duration.Companion.seconds

@Composable
fun SongBottomSheet(
    state: ContentBottomSheetState<Song>,
    navController: NavHostController,
    destinations: Destinations,
    content: @Composable () -> Unit
) {
    val musicManagerProvider = LocalMusicManagerProvider.current
    val commonProvider = LocalCommonProvider.current
    val viewModel = injectedViewModel {
        DaggerSongBottomSheetComponent.builder()
            .commonProvider(commonProvider)
            .musicManagerProvider(musicManagerProvider)
            .build()
            .viewModel
    }

    val context = LocalContext.current
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {}

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {
        viewModelObserver(viewModel, state, scope, navController, destinations)
    }

    ContentBottomSheet(
        state = state,
        sheetContent = { song ->
            val bottomSheetContent = SongBottomSheetContent.build {
                if (song.isCreator)
                    addRow(
                        iconId = R.drawable.ic_edit,
                        textId = R.string.edit_info
                    ) { viewModel.setEvent(SongBottomSheetContract.Event.OnEditInfoClicked(song)) }

                if (song.isFavourite) addRow(
                    iconId = R.drawable.ic_favourite,
                    textId = R.string.remove_from_favourite
                ) { viewModel.setEvent(SongBottomSheetContract.Event.OnRemoveFavouriteClicked(song)) }
                else addRow(
                    iconId = R.drawable.ic_favourite,
                    textId = R.string.add_to_favourite
                ) { viewModel.setEvent(SongBottomSheetContract.Event.OnAddFavouriteClicked(song)) }

                if (song.isCreator || song.isDownloadable)
                    addRow(
                        iconId = R.drawable.ic_download,
                        textId = R.string.download_file
                    ) {
                        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
                                    || context.isPermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE))
                            && (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
                                    || context.isPermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE))
                        ) {
                            viewModel.setEvent(
                                SongBottomSheetContract.Event.OnDownloadFileClicked(song)
                            )
                        } else {
                            requestPermissionLauncher.launch(
                                arrayOf(
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.POST_NOTIFICATIONS
                                )
                            )
                        }
                    }


                if (song.isCreator || song.shareSettings == ShareSettings.Shared.OnlyInvited(true))
                    addRow(
                        iconId = R.drawable.ic_share,
                        textId = R.string.share_settings
                    ) { viewModel.setEvent(SongBottomSheetContract.Event.OnShareSettingsClicked(song)) }

                if (song.shareSettings != ShareSettings.NoSharing)
                    addRow(
                        iconId = R.drawable.ic_link,
                        textId = R.string.copy_link
                    ) { viewModel.setEvent(SongBottomSheetContract.Event.OnCopyLinkClicked(song)) }

                if (song.isCreator)
                    addRow(
                        iconId = R.drawable.ic_delete,
                        textId = R.string.delete_file
                    ) { viewModel.setEvent(SongBottomSheetContract.Event.OnDeleteFileClicked(song)) }
            }
            SongBottomSheet(
                songBottomSheetContent = bottomSheetContent
            )
        },
        content = content
    )
}

private fun viewModelObserver(
    viewModel: SongBottomSheetViewModel,
    state: ContentBottomSheetState<Song>,
    scope: CoroutineScope,
    navController: NavHostController,
    destinations: Destinations
) {
    viewModel.effect.onEach {
        when (it) {
            SongBottomSheetContract.Effect.CloseBottomSheet -> state.hide()
            is SongBottomSheetContract.Effect.OpenEditScreen -> {
                val route = destinations.find<CreateEditFileEntry>().destination(it.songId)
                navController.navigate(route)
            }

            is SongBottomSheetContract.Effect.OpenShareScreen -> {
                val route = destinations.find<ShareFileEntry>().destination(it.songId)
                navController.navigate(route)
            }
        }
    }.launchIn(scope)
}

@Composable
@Preview(showSystemUi = true)
fun BottomSheetPreview() {
    val state = rememberContentBottomSheetState<Song>()
    LaunchedEffect(key1 = true) {
        while (true) {
            state.hide()
            delay(5.seconds)
            state.show(
                Song(
                    "id",
                    "title",
                    "artist",
                    isFavourite = true,
                    isShared = true,
                    isCreator = true,
                    isDownloadable = true,
                    shareSettings = ShareSettings.Shared.AllUsers
                )
            )
            delay(15.seconds)
        }
    }
//    SongBottomSheet(state = state) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.Green)
//        )
//    }
}

fun Context.isPermissionGranted(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

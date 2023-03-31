package com.breaktime.mustune

import android.app.Activity
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.breaktime.mustune.common.Constants
import com.breaktime.mustune.common.find
import com.breaktime.mustune.create_edit_file.api.CreateEditFileEntry
import com.breaktime.mustune.di.LocalAppProvider
import com.breaktime.mustune.login.api.LoginEntry
import com.breaktime.mustune.main.api.MainEntry
import com.breaktime.mustune.music.api.MusicEntry
import com.breaktime.mustune.search_songs.api.SearchSongsEntry
import com.breaktime.mustune.settings.api.SettingsEntry
import com.breaktime.mustune.share_file.api.ShareFileEntry
import com.breaktime.mustune.song.api.SongEntry

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val destinations = LocalAppProvider.current.destinations

    val loginScreen = destinations.find<LoginEntry>()
    val mainScreen = destinations.find<MainEntry>()
    val musicScreen = destinations.find<MusicEntry>()
    val settingsScreen = destinations.find<SettingsEntry>()
    val songScreen = destinations.find<SongEntry>()
    val searchSongsScreen = destinations.find<SearchSongsEntry>()
    val shareFileScreen = destinations.find<ShareFileEntry>()
    val createEditFileScreen = destinations.find<CreateEditFileEntry>()
    BackPressHandler(navController)
    NavHost(navController, startDestination = loginScreen.destination()) {
        with(loginScreen) { navigation(navController, destinations) }
        with(mainScreen) {
            subNavigation(destinations, musicScreen.destination()) {
                with(musicScreen) { composable(navController, destinations) }
                with(settingsScreen) { composable(navController, destinations) }
            }
        }
        with(searchSongsScreen) { composable(navController, destinations) }
        with(songScreen) { composable(navController, destinations) }
        with(shareFileScreen) { composable(navController, destinations) }
        with(createEditFileScreen) { composable(navController, destinations) }
    }
}

@Composable
fun BackPressHandler(
    navController: NavController,
    backPressedDispatcher: OnBackPressedDispatcher? = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
) {
    val backStackSize by navController.rememberBackStackSize()
    val context = LocalContext.current
    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            var lastBackPress = -1L
            var toast: Toast? = null

            override fun handleOnBackPressed() {
                toast?.cancel()
                if (lastBackPress + Constants.BACK_PRESS_DEBOUNCE_MILLS > System.currentTimeMillis()) {
                    (context as? Activity)?.finish()
                } else {
                    toast = Toast
                        .makeText(context, "Press back again to exit", Toast.LENGTH_SHORT)
                        .also { it.show() }
                }
                lastBackPress = System.currentTimeMillis()
            }
        }
    }

    if (backStackSize == 1) DisposableEffect(key1 = backPressedDispatcher) {
        backPressedDispatcher?.addCallback(backCallback)
        onDispose { backCallback.remove() }
    }
}

@Composable
private fun NavController.rememberBackStackSize(): State<Int> {
    val backStackSize = remember {
        mutableStateOf(backQueue.filter { it.destination is ComposeNavigator.Destination }.size)
    }
    DisposableEffect(this) {
        val callback = NavController.OnDestinationChangedListener { _, _, _ ->
            backStackSize.value = backQueue.filter {
                it.destination is ComposeNavigator.Destination
            }.size
        }
        addOnDestinationChangedListener(callback)
        onDispose { removeOnDestinationChangedListener(callback) }
    }
    return backStackSize
}
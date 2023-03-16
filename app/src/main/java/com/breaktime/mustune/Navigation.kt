package com.breaktime.mustune

import android.app.Activity
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.breaktime.mustune.api.LoginEntry
import com.breaktime.mustune.common.Constants
import com.breaktime.mustune.common.find
import com.breaktime.mustune.di.LocalAppProvider
import com.breaktime.mustune.main.api.MainEntry
import com.breaktime.mustune.music.api.MusicEntry
import com.breaktime.mustune.search_songs.api.SearchSongsEntry
import com.breaktime.mustune.settings.api.SettingsEntry
import com.breaktime.mustune.song.api.SongEntry

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val destinations = LocalAppProvider.current.destinations

    val loginScreen = destinations.find<LoginEntry>()
    val mainScreen = destinations.find<MainEntry>()
    val musicScreen = destinations.find<MusicEntry>()
    val songScreen = destinations.find<SongEntry>()
    val searchSongsScreen = destinations.find<SearchSongsEntry>()
    val settingsScreen = destinations.find<SettingsEntry>()
    BackPressHandler(navController)
    NavHost(navController, startDestination = loginScreen.destination()) {
        with(loginScreen) { composable(navController, destinations) }
        with(mainScreen) {
            subNavigation(destinations, musicScreen.destination()) {
                with(musicScreen) { composable(navController, destinations) }
                with(settingsScreen) { composable(navController, destinations) }
            }
        }
        with(searchSongsScreen) { composable(navController, destinations) }
        with(songScreen) { composable(navController, destinations) }
    }
}

@Composable
fun BackPressHandler(
    navController: NavController,
    backPressedDispatcher: OnBackPressedDispatcher? = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
) {
    val backStack by navController.currentBackStackEntryAsState()
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

    if (backStack != null) DisposableEffect(key1 = backPressedDispatcher) {
        backPressedDispatcher?.addCallback(backCallback)
        onDispose { backCallback.remove() }
    }
}
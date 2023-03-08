package com.breaktime.mustune.song.api

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.breaktime.mustune.common.ComposableFeatureEntry

abstract class SongEntry : ComposableFeatureEntry {
    final override val featureRoute = "song/{$ARG_SONG_ID}"

    final override val arguments = listOf(
        navArgument(ARG_SONG_ID) {
            type = NavType.StringType
        }
    )

    fun destination(songId: String) = "song/$songId"

    companion object {
        const val ARG_SONG_ID = "songId"
    }
}
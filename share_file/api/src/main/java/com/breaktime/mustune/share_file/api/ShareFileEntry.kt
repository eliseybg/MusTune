package com.breaktime.mustune.share_file.api

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.breaktime.mustune.common.ComposableFeatureEntry

abstract class ShareFileEntry : ComposableFeatureEntry {
    final override val featureRoute = "share_file?$ARG_SONG_ID={$ARG_SONG_ID}"

    final override val arguments = listOf(
        navArgument(ARG_SONG_ID) {
            nullable = true
            defaultValue = null
            type = NavType.StringType
        }
    )

    fun destination(songId: String) = "share_file?$ARG_SONG_ID=$songId"

    companion object {
        const val ARG_SONG_ID = "songId"
    }
}
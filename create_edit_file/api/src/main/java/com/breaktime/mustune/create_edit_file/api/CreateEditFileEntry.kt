package com.breaktime.mustune.create_edit_file.api

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.breaktime.mustune.common.ComposableFeatureEntry

abstract class CreateEditFileEntry : ComposableFeatureEntry {
    final override val featureRoute = "create_edit_file?$ARG_SONG_ID={$ARG_SONG_ID}"

    final override val arguments = listOf(
        navArgument(ARG_SONG_ID) {
            nullable = true
            defaultValue = null
            type = NavType.StringType
        }
    )

    fun destination(songId: String? = null) =
        "create_edit_file" + songId?.let { "?$ARG_SONG_ID=$songId" }.orEmpty()

    companion object {
        const val ARG_SONG_ID = "songId"
    }
}
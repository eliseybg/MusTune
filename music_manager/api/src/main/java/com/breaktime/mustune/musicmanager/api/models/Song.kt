package com.breaktime.mustune.musicmanager.api.models

data class Song(
    val id: String,
    val title: String,
    val artist: String,
    val isFavourite: Boolean,
    val isShared: Boolean,
    val isCreator: Boolean,
    val isDownloadable: Boolean,
    val shareSettings: ShareSettings
)
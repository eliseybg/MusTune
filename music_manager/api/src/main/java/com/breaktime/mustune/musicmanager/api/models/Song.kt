package com.breaktime.mustune.musicmanager.api.models

data class Song(
    val id: String,
    val title: String,
    val artist: String,
    val isDownloadable: Boolean,
    val shareSettings: ShareSettings
)
package com.breaktime.mustune.musicmanager.impl.data.entities

data class SongInfoBody(
    val songId: String?,
    val title: String,
    val artist: String,
    val isDownloadable: Boolean,
    val shareType: ShareType
)
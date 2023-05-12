package com.breaktime.mustune.musicmanager.api.models

import java.io.File

data class SongFileInfo(
    val title: String,
    val artist: String,
    val file: File
)
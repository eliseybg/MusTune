package com.breaktime.mustune.musicmanager.impl.domain.mapper

import com.breaktime.mustune.musicmanager.api.models.Song
import com.breaktime.mustune.musicmanager.impl.data.entities.SongEntity

fun SongEntity.toSong() = Song(
    id = id,
    title = title,
    artist = artist,
    isDownloadable = isDownloadable,
    shareSettings = shareType.toShareSettings()
)
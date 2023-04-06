package com.breaktime.mustune.musicmanager.impl.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar
import java.util.Date

@Entity(tableName = "SongEntity")
data class SongEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    val artist: String,
    val isDownloadable: Boolean,
    val isFavourite: Boolean,
    val isShared: Boolean,
    val isCreator: Boolean,
    val shareType: ShareType,
    val createdAt: Date = Calendar.getInstance().time,
    val updatedAt: Date = Calendar.getInstance().time,
    val createdBy: String?
)
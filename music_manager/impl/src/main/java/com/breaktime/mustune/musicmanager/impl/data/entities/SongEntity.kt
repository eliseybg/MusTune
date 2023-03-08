package com.breaktime.mustune.musicmanager.impl.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SongEntity")
data class SongEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    val author: String,
    val link: String,
    val tab: String,
    var createdAt: Long = System.currentTimeMillis()
)
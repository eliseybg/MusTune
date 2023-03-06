package com.breaktime.mustune.musicmanager.impl.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RemoteKeysEntity")
data class RemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val songId: String,
    val prevPage: Int?,
    val nextPage: Int?,
    val tab: String,
    val createdAt: Long = System.currentTimeMillis()
)

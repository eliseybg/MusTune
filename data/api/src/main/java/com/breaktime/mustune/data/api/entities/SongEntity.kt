package com.breaktime.mustune.data.api.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songEntity")
data class SongEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val author: String,
    val link: String,
)
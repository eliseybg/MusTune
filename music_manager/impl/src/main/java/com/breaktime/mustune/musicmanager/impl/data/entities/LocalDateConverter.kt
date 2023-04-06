package com.breaktime.mustune.musicmanager.impl.data.entities

import androidx.room.TypeConverter
import java.util.Date

class LocalDateConverter {
    @TypeConverter
    fun fromLong(value: Long): Date = Date(value)

    @TypeConverter
    fun toLong(date: Date): Long = date.time
}
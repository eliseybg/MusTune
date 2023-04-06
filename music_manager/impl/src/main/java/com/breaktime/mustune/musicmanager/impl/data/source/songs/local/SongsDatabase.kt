package com.breaktime.mustune.musicmanager.impl.data.source.songs.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.breaktime.mustune.musicmanager.impl.data.entities.LocalDateConverter
import com.breaktime.mustune.musicmanager.impl.data.entities.RemoteKeysEntity
import com.breaktime.mustune.musicmanager.impl.data.entities.SongEntity

@Database(entities = [SongEntity::class, RemoteKeysEntity::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateConverter::class)
abstract class SongsDatabase : RoomDatabase() {
    abstract val songDao: SongsDao
    abstract val remoteKeysDao: RemoteKeysDao
}
package com.breaktime.mustune.musicmanager.impl.data.source.songs.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.breaktime.mustune.musicmanager.impl.data.entities.RemoteKeysEntity
import com.breaktime.mustune.musicmanager.impl.data.entities.SongEntity

@Database(entities = [SongEntity::class, RemoteKeysEntity::class], version = 1)
abstract class SongsDatabase : RoomDatabase() {
    abstract val songDao: SongsDao
    abstract val remoteKeysDao: RemoteKeysDao
}
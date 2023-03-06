package com.breaktime.mustune.musicmanager.impl.data.source.songs.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.breaktime.mustune.musicmanager.impl.data.entities.SongEntity
import com.breaktime.mustune.musicmanager.api.models.MusicTab

@Dao
interface SongsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(songEntity: List<SongEntity>)

    @Query("SELECT * FROM SongEntity WHERE tab = :tab")
    fun getSongsInfo(tab: String): PagingSource<Int, SongEntity>

    @Query("DELETE FROM SongEntity WHERE tab = :tab")
    suspend fun clearAllSongs(tab: String)

    @Query("SELECT createdAt FROM SongEntity ORDER BY createdAt DESC LIMIT 1")
    suspend fun getCreationTime(): Long?
}
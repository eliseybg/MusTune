package com.breaktime.mustune.musicmanager.impl.data.source.songs.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.breaktime.mustune.musicmanager.impl.data.entities.SongEntity

@Dao
interface SongsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSong(it: SongEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSong(it: SongEntity)

    @Query("DElETE FROM SongEntity WHERE id = :songId")
    fun deleteSong(songId: String)

    @Transaction
    fun insertSongAndRead(it: SongEntity): SongEntity? {
        insertSong(it)
        return getSong(it.id)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(songEntity: List<SongEntity>)

    @Query("SELECT * FROM SongEntity WHERE id = :songId")
    fun getSong(songId: String): SongEntity?

    @Query("SELECT * FROM SongEntity WHERE tab = :tab")
    fun getSongsInfo(tab: String): PagingSource<Int, SongEntity>

    @Query("DELETE FROM SongEntity WHERE tab = :tab")
    suspend fun clearAllSongs(tab: String)

    @Query("SELECT createdAt FROM SongEntity ORDER BY createdAt DESC LIMIT 1")
    suspend fun getCreationTime(): Long?
}
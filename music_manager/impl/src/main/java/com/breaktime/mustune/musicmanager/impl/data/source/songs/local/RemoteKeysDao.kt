package com.breaktime.mustune.musicmanager.impl.data.source.songs.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.breaktime.mustune.musicmanager.impl.data.entities.RemoteKeysEntity

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeysEntity>)

    @Query("SELECT * FROM RemoteKeysEntity WHERE songId = :id")
    suspend fun getRemoteKeyBySongId(id: String): RemoteKeysEntity?

    @Query("DELETE FROM RemoteKeysEntity WHERE tab = :tab")
    suspend fun clearRemoteKeys(tab: String)

    @Query("SELECT createdAt FROM RemoteKeysEntity WHERE tab = :tab ORDER BY createdAt DESC LIMIT 1")
    suspend fun getCreationTime(tab: String): Long?
}
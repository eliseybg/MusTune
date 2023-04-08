package com.breaktime.mustune.musicmanager.impl.data.source.songs.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.breaktime.mustune.musicmanager.impl.data.entities.SongEntity
import com.breaktime.mustune.musicmanager.impl.data.entities.TabQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

@Dao
interface SongsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(it: SongEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSong(it: SongEntity)

    @Query("DElETE FROM SongEntity WHERE id = :songId")
    suspend fun deleteSong(songId: String)

    @Transaction
    suspend fun insertSongAndRead(it: SongEntity): SongEntity? {
        insertSong(it)
        return getSong(it.id)
    }

    fun getSongsCategories(): Flow<List<TabQuery>> = combine(
        listOf(TabQuery.EXPLORE, TabQuery.FAVOURITE, TabQuery.SHARED, TabQuery.PERSONAL)
            .map { tab ->
                val query = tab.toQuery()
                getSongInfoIfExist(query.first, query.second, query.third)
            }
    ) { list ->
        buildList {
            if (list.getOrNull(0) != null) add(TabQuery.EXPLORE)
            if (list.getOrNull(1) != null) add(TabQuery.FAVOURITE)
            if (list.getOrNull(2) != null) add(TabQuery.SHARED)
            if (list.getOrNull(3) != null) add(TabQuery.PERSONAL)
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(songEntity: List<SongEntity>)

    @Query("SELECT * FROM SongEntity WHERE id = :songId")
    suspend fun getSong(songId: String): SongEntity?

    fun getPagingSongsInfo(tab: TabQuery): PagingSource<Int, SongEntity> {
        val query = tab.toQuery()
        return getPagingSongsInfo(query.first, query.second, query.third)
    }

    @Query(
        "SELECT * FROM SongEntity WHERE " +
                "(:isFavourite IS NULL OR isFavourite = :isFavourite)" +
                "AND (:isCreator IS NULL OR isCreator = :isCreator)" +
                "AND (:isShared IS NULL OR isShared = :isShared)"
    )
    fun getPagingSongsInfo(
        isFavourite: Boolean? = null,
        isCreator: Boolean? = null,
        isShared: Boolean? = null
    ): PagingSource<Int, SongEntity>

    @Query(
        "SELECT * FROM SongEntity WHERE " +
                "(:isFavourite IS NULL OR isFavourite = :isFavourite)" +
                "AND (:isCreator IS NULL OR isCreator = :isCreator)" +
                "AND (:isShared IS NULL OR isShared = :isShared) LIMIT 1"
    )
    fun getSongInfoIfExist(
        isFavourite: Boolean? = null,
        isCreator: Boolean? = null,
        isShared: Boolean? = null
    ): Flow<SongEntity?>

    suspend fun clearAllSongs(tab: TabQuery) {
        val query = tab.toQuery()
        return clearAllSongs(query.first, query.second, query.third)
    }

    @Query(
        "DELETE FROM SongEntity WHERE " +
                "(:isFavourite = '' OR isFavourite = :isFavourite)" +
                "AND (:isCreator = '' OR isCreator = :isCreator)" +
                "AND (:isShared = '' OR isShared = :isShared)"
    )
    suspend fun clearAllSongs(
        isFavourite: Boolean? = null,
        isCreator: Boolean? = null,
        isShared: Boolean? = null
    )

    private fun TabQuery.toQuery(): Triple<Boolean?, Boolean?, Boolean?> = when (this) {
        TabQuery.EXPLORE -> Triple(null, false, false)
        TabQuery.FAVOURITE -> Triple(true, null, null)
        TabQuery.PERSONAL -> Triple(null, true, false)
        TabQuery.SHARED -> Triple(null, false, true)
        TabQuery.UNKNOWN -> Triple(null, null, null)
    }
}
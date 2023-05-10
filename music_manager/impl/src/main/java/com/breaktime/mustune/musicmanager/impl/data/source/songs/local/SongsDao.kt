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
                val query = tab.getQuery()
                getSongInfoIfExist(
                    isFavourite = query.isFavourite,
                    isShared = query.isShared,
                    isCreator = query.isCreator
                )
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
        val query = tab.getQuery()
        return getPagingSongsInfo(
            isFavourite = query.isFavourite,
            isShared = query.isShared,
            isCreator = query.isCreator
        )
    }

    @Query(
        "SELECT * FROM SongEntity WHERE " +
                "(:isFavourite IS NULL OR isFavourite = :isFavourite)" +
                "AND (:isShared IS NULL OR isShared = :isShared)" +
                "AND (:isCreator IS NULL OR isCreator = :isCreator) ORDER BY createdAt"
    )
    fun getPagingSongsInfo(
        isFavourite: Boolean? = null,
        isShared: Boolean? = null,
        isCreator: Boolean? = null,
    ): PagingSource<Int, SongEntity>

    @Query(
        "SELECT * FROM SongEntity WHERE " +
                "(:isFavourite IS NULL OR isFavourite = :isFavourite)" +
                "AND (:isShared IS NULL OR isShared = :isShared)" +
                "AND (:isCreator IS NULL OR isCreator = :isCreator)"
    )
    fun getSongInfoIfExist(
        isFavourite: Boolean? = null,
        isShared: Boolean? = null,
        isCreator: Boolean? = null,
    ): Flow<SongEntity?>

    suspend fun clearAllSongs(tab: TabQuery) {
        val query = tab.getQuery()
        return clearAllSongs(
            isFavourite = query.isFavourite,
            isShared = query.isShared,
            isCreator = query.isCreator
        )
    }

    @Query(
        "DELETE FROM SongEntity WHERE " +
                "(:isFavourite = NULL OR isFavourite = :isFavourite)" +
                "AND (:isShared = NULL OR isShared = :isShared)" +
                "AND (:isCreator = NULL OR isCreator = :isCreator)"
    )
    suspend fun clearAllSongs(
        isFavourite: Boolean? = null,
        isShared: Boolean? = null,
        isCreator: Boolean? = null,
    )
}
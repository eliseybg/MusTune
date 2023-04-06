package com.breaktime.mustune.musicmanager.impl.domain.repository

import androidx.paging.PagingData
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.api.models.SearchFilter
import com.breaktime.mustune.musicmanager.impl.data.entities.ShareType
import com.breaktime.mustune.musicmanager.impl.data.entities.SongEntity
import com.breaktime.mustune.musicmanager.impl.data.entities.TabQuery
import kotlinx.coroutines.flow.Flow
import java.io.File

interface SongsRepository {
    suspend fun getSong(songId: String, isForce: Boolean = false): SongEntity?

    suspend fun getUserMusicTabs(isForce: Boolean): List<TabQuery>

    fun getSongs(tab: MusicTab): Flow<PagingData<SongEntity>>

    fun searchSongs(searchText: String, searchFilter: SearchFilter): Flow<PagingData<SongEntity>>

    suspend fun addSong(
        title: String,
        artist: String,
        isDownloadable: Boolean,
        shareType: ShareType,
        file: File
    )

    suspend fun editSong(
        songId: String,
        title: String,
        artist: String,
        isDownloadable: Boolean,
        shareType: ShareType
    )

    suspend fun deleteSong(songId: String)
}
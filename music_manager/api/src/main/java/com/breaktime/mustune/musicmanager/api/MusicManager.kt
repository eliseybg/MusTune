package com.breaktime.mustune.musicmanager.api

import android.net.Uri
import androidx.paging.PagingData
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.api.models.SearchFilter
import com.breaktime.mustune.musicmanager.api.models.ShareSettings
import com.breaktime.mustune.musicmanager.api.models.Song
import com.breaktime.mustune.musicmanager.api.models.SongFileInfo
import com.breaktime.mustune.musicmanager.api.models.TabSetup
import kotlinx.coroutines.flow.Flow
import java.io.File

interface MusicManager {
    suspend fun getSong(songId: String, isForce: Boolean = false): Outcome<Song>

    suspend fun getSongFile(songId: String): Outcome<SongFileInfo>

    fun getMusicTabSetup(tab: MusicTab): TabSetup

    fun getUserMusicTabs(isForce: Boolean): Flow<List<MusicTab>>

    fun searchSongs(searchText: String, searchFilter: SearchFilter): Flow<PagingData<Song>>

    suspend fun addSong(
        title: String,
        artist: String,
        isDownloadable: Boolean,
        shareSettings: ShareSettings,
        file: Uri
    ): Outcome<Unit>

    suspend fun editSong(
        songId: String,
        title: String,
        artist: String,
        isDownloadable: Boolean,
        shareSettings: ShareSettings
    ): Outcome<Unit>

    suspend fun deleteSong(songId: String): Outcome<Unit>

    suspend fun addToFavourite(songId: String): Outcome<Unit>

    suspend fun removeFromFavourite(songId: String): Outcome<Unit>

    suspend fun clearStorage(): Outcome<Unit>
}
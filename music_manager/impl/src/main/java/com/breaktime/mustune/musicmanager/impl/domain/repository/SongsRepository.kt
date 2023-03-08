package com.breaktime.mustune.musicmanager.impl.domain.repository

import androidx.paging.PagingData
import com.breaktime.mustune.musicmanager.impl.data.entities.SongEntity
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import kotlinx.coroutines.flow.Flow

interface SongsRepository {
    fun getSongs(tab: MusicTab): Flow<PagingData<SongEntity>>
    fun searchSongs(searchText: String): Flow<PagingData<SongEntity>>
}
package com.breaktime.mustune.musicmanager.api

import androidx.paging.PagingData
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.api.models.Song
import com.breaktime.mustune.musicmanager.api.models.TabSetup
import kotlinx.coroutines.flow.Flow

interface MusicManager {
    fun getMusicTabSetup(tab: MusicTab): TabSetup
    fun searchSongs(searchText: String, tab: MusicTab): Flow<PagingData<Song>>
}
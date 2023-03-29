package com.breaktime.mustune.musicmanager.api.models

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

data class TabSetup(val tab: MusicTab, val songs: Flow<PagingData<Song>>)
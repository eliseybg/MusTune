package com.breaktime.mustune.search_songs.impl.presentation

import androidx.paging.PagingData
import com.breaktime.mustune.common.presentation.UiEffect
import com.breaktime.mustune.common.presentation.UiEvent
import com.breaktime.mustune.common.presentation.UiState
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.api.models.Song
import com.breaktime.mustune.musicmanager.api.models.TabSetup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class SearchSongsContract {
    sealed class Event : UiEvent {
        data class UpdateSearchText(val searchText: String) : Event()
        object OnFilterClicked : Event()
    }

    data class State(
        val searchText: String = "",
        val isFilterShown: Boolean = false,
        val songs: Flow<PagingData<Song>> = emptyFlow()
    ) : UiState

    sealed class Effect : UiEffect
}
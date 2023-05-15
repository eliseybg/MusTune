package com.breaktime.mustune.search_songs.impl.presentation

import androidx.paging.PagingData
import com.breaktime.mustune.common.UiText
import com.breaktime.mustune.common.presentation.UiEffect
import com.breaktime.mustune.common.presentation.UiEvent
import com.breaktime.mustune.common.presentation.UiState
import com.breaktime.mustune.musicmanager.api.models.SearchFilter
import com.breaktime.mustune.musicmanager.api.models.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class SearchSongsContract {
    sealed class Event : UiEvent {
        data class UpdateSearchText(val searchText: String) : Event()
        data class UpdateFilter(val filterState: SearchFilter) : Event()
    }

    data class State(
        val searchText: String = "",
        val searchFilter: SearchFilter = SearchFilter(),
        val songs: Flow<PagingData<Song>> = emptyFlow()
    ) : UiState

    sealed class Effect : UiEffect {
        data class ErrorMessage(val message: UiText) : Effect()
    }
}
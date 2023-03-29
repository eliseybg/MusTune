package com.breaktime.mustune.search_songs.impl.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.breaktime.mustune.common.Constants
import com.breaktime.mustune.common.presentation.BaseViewModel
import com.breaktime.mustune.musicmanager.api.MusicManager
import com.breaktime.mustune.musicmanager.api.models.SearchFilter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

class SearchSongsViewModel @Inject constructor(
    private val musicManager: MusicManager
) : BaseViewModel<SearchSongsContract.Event, SearchSongsContract.State, SearchSongsContract.Effect>() {
    override fun createInitialState() = SearchSongsContract.State()

    private val searchText = MutableStateFlow("")
    private val searchFilter = MutableStateFlow(SearchFilter())

    private val songs = combine(
        searchText.debounce(Constants.SEARCH_DEBOUNCE_MILLS),
        searchFilter
    ) { searchText, searchFilter ->
        searchText to searchFilter
    }.flatMapLatest { (searchText, searchFilter) ->
        flow { emit(musicManager.searchSongs(searchText, searchFilter).cachedIn(viewModelScope)) }
    }

    init {
        combine(searchText, searchFilter, songs) { searchText, searchFilter, songs ->
            setState { copy(searchText = searchText, searchFilter = searchFilter, songs = songs) }
        }.launchIn(viewModelScope)
    }

    override fun handleEvent(event: SearchSongsContract.Event) {
        when (event) {
            is SearchSongsContract.Event.UpdateSearchText -> searchText.value = event.searchText
            is SearchSongsContract.Event.UpdateFilter -> searchFilter.value = event.filterState
        }
    }
}
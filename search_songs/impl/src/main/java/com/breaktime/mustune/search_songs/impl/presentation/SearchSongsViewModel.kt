package com.breaktime.mustune.search_songs.impl.presentation

import androidx.lifecycle.viewModelScope
import com.breaktime.mustune.common.Constants
import com.breaktime.mustune.common.presentation.BaseViewModel
import com.breaktime.mustune.musicmanager.api.MusicManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SearchSongsViewModel @Inject constructor(
    private val musicManager: MusicManager
) : BaseViewModel<SearchSongsContract.Event, SearchSongsContract.State, SearchSongsContract.Effect>() {
    override fun createInitialState() = SearchSongsContract.State()

    private val searchText = MutableStateFlow("")
    private val isFilterShown = MutableStateFlow(false)

    private val songs = searchText.debounce(Constants.SEARCH_DEBOUNCE_MILLS)
        .flatMapLatest { searchText -> flow { emit(musicManager.searchSongs(searchText)) } }

    init {
        isFilterShown.onEach { isFilterShown ->
            setState { copy(isFilterShown = isFilterShown) }
        }.launchIn(viewModelScope)
        searchText.onEach { searchText ->
            setState { copy(searchText = searchText) }
        }.launchIn(viewModelScope)
        songs.onEach { songs ->
            setState { copy(songs = songs) }
        }.launchIn(viewModelScope)
    }

    override fun handleEvent(event: SearchSongsContract.Event) {
        when (event) {
            is SearchSongsContract.Event.UpdateSearchText -> searchText.value = event.searchText
            SearchSongsContract.Event.OnFilterClicked -> isFilterShown.getAndUpdate { !it }
        }
    }
}
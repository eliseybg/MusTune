package com.breaktime.mustune.music.impl.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.breaktime.mustune.common.presentation.BaseViewModel
import com.breaktime.mustune.musicmanager.api.MusicManager
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MusicViewModel @Inject constructor(
    private val musicManager: MusicManager
) : BaseViewModel<MusicContract.Event, MusicContract.State, MusicContract.Effect>() {
    override fun createInitialState() = MusicContract.State()

    private val currentTab = MutableStateFlow(MusicTab.EXPLORE)
    private val screenTabs = flowOf(MusicTab.values().toList())

    private val tabsSetup = screenTabs.map { tabs ->
        tabs.map { tab -> musicManager.getMusicTabSetup(tab) }
            .map { tabSetup -> tabSetup.copy(songs = tabSetup.songs.cachedIn(viewModelScope)) }
    }

    init {
        combine(currentTab, screenTabs, tabsSetup) { currentTab, screenTabs, tabsSetup ->
            setState {
                copy(currentTab = currentTab, screenTabs = screenTabs, tabsSetup = tabsSetup)
            }
        }.launchIn(viewModelScope)
    }

    override fun handleEvent(event: MusicContract.Event) {

    }
}
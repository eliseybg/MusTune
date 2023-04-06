package com.breaktime.mustune.music.impl.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.common.presentation.BaseViewModel
import com.breaktime.mustune.musicmanager.api.MusicManager
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class MusicViewModel @Inject constructor(
    private val musicManager: MusicManager
) : BaseViewModel<MusicContract.Event, MusicContract.State, MusicContract.Effect>() {
    override fun createInitialState() = MusicContract.State()

    private val currentTab = MutableStateFlow(MusicTab.EXPLORE)
    private val loadScreenTabsEvent = MutableSharedFlow<Boolean>(replay = 1)
    private val screenTabs = loadScreenTabsEvent.map { musicManager.getUserMusicTabs(it) }
        .filterIsInstance<Outcome.Success<List<MusicTab>>>()
        .map { it.value }
        .shareIn(viewModelScope, SharingStarted.Eagerly)
        .distinctUntilChanged()

    private val tabsSetup = screenTabs.map { tabs ->
        tabs.map { tab -> musicManager.getMusicTabSetup(tab) }
            .map { tabSetup -> tabSetup.copy(songs = tabSetup.songs.cachedIn(viewModelScope)) }
    }

    init {
        loadScreenTabsEvent.tryEmit(true)
        combine(currentTab, screenTabs, tabsSetup) { currentTab, screenTabs, tabsSetup ->
            setState {
                copy(currentTab = currentTab, screenTabs = screenTabs, tabsSetup = tabsSetup)
            }
        }.launchIn(viewModelScope)
    }

    override fun handleEvent(event: MusicContract.Event) {
        when (event) {
            is MusicContract.Event.UpdateSongTabs -> loadScreenTabsEvent.tryEmit(event.isForce)
        }
    }
}
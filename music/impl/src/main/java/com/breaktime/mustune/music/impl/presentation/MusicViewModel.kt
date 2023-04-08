package com.breaktime.mustune.music.impl.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.breaktime.mustune.common.extentions.mapWithPrev
import com.breaktime.mustune.common.presentation.BaseViewModel
import com.breaktime.mustune.musicmanager.api.MusicManager
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.api.models.TabSetup
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class MusicViewModel @Inject constructor(
    private val musicManager: MusicManager
) : BaseViewModel<MusicContract.Event, MusicContract.State, MusicContract.Effect>() {
    override fun createInitialState() = MusicContract.State()

    private val currentTab = MutableStateFlow(MusicTab.EXPLORE)
    private val loadScreenTabsEvent = MutableSharedFlow<Boolean>(replay = 1)
    private val screenTabs = loadScreenTabsEvent.flatMapLatest { musicManager.getUserMusicTabs(it) }
        .shareIn(viewModelScope, SharingStarted.Eagerly)
        .distinctUntilChanged()

    private val tabsSetup = screenTabs.mapWithPrev<List<MusicTab>, List<TabSetup>> { prev, tabs ->
        val (prevTabs, prevResult) = prev ?: Pair(emptyList(), emptyList())

        tabs.map { tab ->
            val prevTabIndex = prevTabs.indexOf(tab)
            if (prevTabIndex == -1) {
                val tabSetup = musicManager.getMusicTabSetup(tab)
                tabSetup.copy(songs = tabSetup.songs.cachedIn(viewModelScope))
            } else prevResult[prevTabIndex]
        }
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
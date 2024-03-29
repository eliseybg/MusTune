package com.breaktime.mustune.song.impl.presentation

import com.breaktime.mustune.common.presentation.UiEffect
import com.breaktime.mustune.common.presentation.UiEvent
import com.breaktime.mustune.common.presentation.UiState
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.api.models.TabSetup

class SongContract {
    sealed class Event : UiEvent

    data class State(
        val currentTab: MusicTab = MusicTab.EXPLORE,
        val screenTabs: List<MusicTab> = emptyList(),
        val tabsSetup: List<TabSetup> = emptyList()
    ) : UiState

    sealed class Effect : UiEffect
}
package com.breaktime.mustune.music.impl.presentation

import com.breaktime.mustune.common.presentation.UiEffect
import com.breaktime.mustune.common.presentation.UiEvent
import com.breaktime.mustune.common.presentation.UiState
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.api.models.TabSetup

class MusicContract {
    sealed class Event : UiEvent {
        data class UpdateSongTabs(val isForce: Boolean = false) : Event()
    }

    data class State(
        val currentTab: MusicTab = MusicTab.EXPLORE,
        val screenTabs: List<MusicTab> = emptyList(),
        val tabsSetup: List<TabSetup> = emptyList()
    ) : UiState

    sealed class Effect : UiEffect {
        data class ErrorMessage(val message: String) : Effect()
    }
}
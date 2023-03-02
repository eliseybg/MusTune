package com.breaktime.mustune.music.impl.presentation

import com.breaktime.mustune.common.presentation.UiEffect
import com.breaktime.mustune.common.presentation.UiEvent
import com.breaktime.mustune.common.presentation.UiState
import com.breaktime.mustune.music.impl.domain.model.SongInfo

class SettingsContract {
    sealed class Event : UiEvent

    class State(
        val currentTab: MusicTab = MusicTab.Explore,
        val tabs: List<MusicTab> = emptyList(),
        val songs: Map<MusicTab, List<SongInfo>> = emptyMap()
    ) : UiState

    sealed class Effect : UiEffect
}
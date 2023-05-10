package com.breaktime.mustune.song_bottom_sheet

import com.breaktime.mustune.common.presentation.UiEffect
import com.breaktime.mustune.common.presentation.UiEvent
import com.breaktime.mustune.common.presentation.UiState
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.api.models.Song
import com.breaktime.mustune.musicmanager.api.models.TabSetup

class SongBottomSheetContract {
    sealed class Event : UiEvent {
        data class OnEditInfoClicked(val song: Song) : Event()
        data class OnRemoveFavouriteClicked(val song: Song) : Event()
        data class OnAddFavouriteClicked(val song: Song) : Event()
        data class OnDownloadFileClicked(val song: Song) : Event()
        data class OnShareSettingsClicked(val song: Song) : Event()
        data class OnCopyLinkClicked(val song: Song) : Event()
        data class OnDeleteFileClicked(val song: Song) : Event()
    }

    data class State(
        val currentTab: MusicTab = MusicTab.EXPLORE,
        val screenTabs: List<MusicTab> = emptyList(),
        val tabsSetup: List<TabSetup> = emptyList()
    ) : UiState

    sealed class Effect : UiEffect {
        object CloseBottomSheet : Effect()
        data class OpenEditScreen(val songId: String) : Effect()
        data class OpenShareScreen(val songId: String) : Effect()
    }
}
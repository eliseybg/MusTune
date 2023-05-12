package com.breaktime.mustune.song.impl.presentation

import com.breaktime.mustune.common.presentation.UiEffect
import com.breaktime.mustune.common.presentation.UiEvent
import com.breaktime.mustune.common.presentation.UiState
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.api.models.TabSetup
import java.io.File

class SongContract {
    sealed class Event : UiEvent

    data class State(
        val songName: String = "",
        val file: File? = null
    ) : UiState

    sealed class Effect : UiEffect
}
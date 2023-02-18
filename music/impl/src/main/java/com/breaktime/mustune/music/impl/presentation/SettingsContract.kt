package com.breaktime.mustune.music.impl.presentation

import com.breaktime.mustune.common.presentation.UiEffect
import com.breaktime.mustune.common.presentation.UiEvent
import com.breaktime.mustune.common.presentation.UiState

class SettingsContract {
    sealed class Event : UiEvent

    class State : UiState

    sealed class Effect : UiEffect
}
package com.breaktime.mustune.settings.impl.presentation

import com.breaktime.mustune.common.presentation.UiEffect
import com.breaktime.mustune.common.presentation.UiEvent
import com.breaktime.mustune.common.presentation.UiState

class SettingsContract {
    sealed class Event : UiEvent {
        object OnChangeNotificationEnabled : Event()
        object OnChangeDarkModeEnabled : Event()
    }

    data class State(
        val isNotificationsEnabled: Boolean = false,
        val isDarkModeEnabled: Boolean = false,
        val currentLanguage: String = "English"
    ) : UiState

    sealed class Effect : UiEffect
}
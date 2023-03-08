package com.breaktime.mustune.settings.impl.presentation

import androidx.lifecycle.viewModelScope
import com.breaktime.mustune.common.presentation.BaseViewModel
import com.breaktime.mustune.settings_manager.api.SettingsManager
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val settingsManager: SettingsManager
) : BaseViewModel<SettingsContract.Event, SettingsContract.State, SettingsContract.Effect>() {
    override fun createInitialState() = SettingsContract.State()

    init {
        settingsManager.isNotificationsEnabled()
            .combine(settingsManager.isDarkModeEnabled()) { isNotificationsEnabled, isDarkModeEnabled ->
                setState {
                    copy(
                        isNotificationsEnabled = isNotificationsEnabled,
                        isDarkModeEnabled = isDarkModeEnabled
                    )
                }
            }.launchIn(viewModelScope)
    }

    override fun handleEvent(event: SettingsContract.Event) {
        when (event) {
            is SettingsContract.Event.OnChangeNotificationEnabled -> changeNotificationEnabled()
            is SettingsContract.Event.OnChangeDarkModeEnabled -> changeDarkModeEnabled()
        }
    }

    private fun changeNotificationEnabled() = viewModelScope.launch {
        settingsManager.setNotificationsEnabled(!uiState.value.isNotificationsEnabled)
    }

    private fun changeDarkModeEnabled() = viewModelScope.launch {
        settingsManager.setDarkModeEnabled(!uiState.value.isDarkModeEnabled)
    }
}
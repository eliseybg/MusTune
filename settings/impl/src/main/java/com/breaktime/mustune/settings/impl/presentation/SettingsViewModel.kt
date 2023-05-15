package com.breaktime.mustune.settings.impl.presentation

import androidx.lifecycle.viewModelScope
import com.breaktime.mustune.common.presentation.BaseViewModel
import com.breaktime.mustune.musicmanager.api.MusicManager
import com.breaktime.mustune.session_manager.api.SessionManager
import com.breaktime.mustune.settings_manager.api.SettingsManager
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val settingsManager: SettingsManager,
    private val sessionManager: SessionManager,
    private val musicManager: MusicManager
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
            is SettingsContract.Event.OnChangeNotificationClicked -> changeNotificationEnabled()
            is SettingsContract.Event.OnChangeDarkModeClicked -> changeDarkModeEnabled()
            is SettingsContract.Event.OnLogOutClicked -> onLogOut()
            is SettingsContract.Event.OnDeleteAccountClicked -> onDeleteAccount()
        }
    }

    private fun changeNotificationEnabled() = viewModelScope.launch {
        settingsManager.setNotificationsEnabled(!uiState.value.isNotificationsEnabled)
    }

    private fun changeDarkModeEnabled() = viewModelScope.launch {
        settingsManager.setDarkModeEnabled(!uiState.value.isDarkModeEnabled)
    }

    private fun onLogOut() = viewModelScope.launch {
        sessionManager.logout()
        musicManager.clearStorage()
    }

    private fun onDeleteAccount() = viewModelScope.launch {
        runCatching { sessionManager.logoutAndDelete() }
        musicManager.clearStorage()
    }
}
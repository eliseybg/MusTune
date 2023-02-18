package com.breaktime.mustune.settings.api.presentation

import androidx.lifecycle.viewModelScope
import com.breaktime.mustune.common.presentation.BaseViewModel
import com.breaktime.mustune.settings.api.domain.use_case.*
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val getCurrentLanguageUseCase: GetCurrentLanguageUseCase,
    private val isDarkModeEnabledUseCase: IsDarkModeEnabledUseCase,
    private val isNotificationsEnabledUseCase: IsNotificationsEnabledUseCase,
    private val setCurrentLanguageUseCase: SetCurrentLanguageUseCase,
    private val setDarkModeEnabledUseCase: SetDarkModeEnabledUseCase,
    private val setNotificationsEnabledUseCase: SetNotificationsEnabledUseCase
) : BaseViewModel<SettingsContract.Event, SettingsContract.State, SettingsContract.Effect>() {
    override fun createInitialState() = SettingsContract.State()

    init {
        isNotificationsEnabledUseCase().combine(isDarkModeEnabledUseCase()) { isNotificationsEnabled, isDarkModeEnabled ->
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
        setNotificationsEnabledUseCase(!uiState.value.isNotificationsEnabled)
    }

    private fun changeDarkModeEnabled() = viewModelScope.launch {
        setDarkModeEnabledUseCase(!uiState.value.isDarkModeEnabled)
    }
}
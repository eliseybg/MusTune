package com.breaktime.mustune.settings.impl.presentation

import androidx.lifecycle.viewModelScope
import com.breaktime.mustune.common.presentation.BaseViewModel
import com.breaktime.mustune.settings.impl.domain.use_case.*
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val getCurrentLanguageFlowUseCase: GetCurrentLanguageFlowUseCase,
    private val isDarkModeEnabledUseCase: IsDarkModeEnabledFlowUseCase,
    private val isNotificationsEnabledUseCase: IsNotificationsEnabledFlowUseCase,
    private val setCurrentLanguageUseCase: SetCurrentLanguageUseCase,
    private val setDarkModeEnabledUseCase: SetDarkModeEnabledUseCase,
    private val setNotificationsEnabledUseCase: SetNotificationsEnabledUseCase
) : BaseViewModel<SettingsContract.Event, SettingsContract.State, SettingsContract.Effect>() {
    override fun createInitialState() = SettingsContract.State()

    init {
        isNotificationsEnabledUseCase(IsNotificationsEnabledFlowUseCase.Params).combine(
            isDarkModeEnabledUseCase(IsDarkModeEnabledFlowUseCase.Params)
        ) { isNotificationsEnabled, isDarkModeEnabled ->
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
        val params = SetNotificationsEnabledUseCase.Params(!uiState.value.isNotificationsEnabled)
        setNotificationsEnabledUseCase(params)
    }

    private fun changeDarkModeEnabled() = viewModelScope.launch {
        val params = SetDarkModeEnabledUseCase.Params(!uiState.value.isDarkModeEnabled)
        setDarkModeEnabledUseCase(params)
    }
}
package com.breaktime.mustune.settings.api.domain.use_case

import com.breaktime.mustune.settings.api.domain.repository.SettingsRepository
import javax.inject.Inject

class SetNotificationsEnabledUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {
    suspend operator fun invoke(isEnabled: Boolean) {
        settingsRepository.setNotificationsEnabled(isEnabled)
    }
}
package com.breaktime.mustune.settings.api.domain.use_case

import com.breaktime.mustune.data.api.repository.SettingsRepository
import javax.inject.Inject

class SetDarkModeEnabledUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {
    suspend operator fun invoke(isEnabled: Boolean) {
        settingsRepository.setDarkModeEnabled(isEnabled)
    }
}
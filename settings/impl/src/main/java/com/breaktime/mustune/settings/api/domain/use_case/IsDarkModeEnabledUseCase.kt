package com.breaktime.mustune.settings.api.domain.use_case

import com.breaktime.mustune.data.api.repository.SettingsRepository
import javax.inject.Inject

class IsDarkModeEnabledUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {
    operator fun invoke() = settingsRepository.isDarkModeEnabled()
}
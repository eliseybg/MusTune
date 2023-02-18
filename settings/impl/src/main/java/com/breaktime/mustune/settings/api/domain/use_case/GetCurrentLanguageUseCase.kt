package com.breaktime.mustune.settings.api.domain.use_case

import com.breaktime.mustune.settings.api.domain.repository.SettingsRepository
import javax.inject.Inject

class GetCurrentLanguageUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {
    operator fun invoke() = settingsRepository.getCurrentLanguage()
}